package com.msa.instagram.clone.account.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.command.AccountUpdateCommand;
import com.msa.instagram.clone.account.enums.AccountAggregateField;
import com.msa.instagram.clone.account.enums.Gender;
import com.msa.instagram.clone.account.event.create.AccountCreateEvent;
import com.msa.instagram.clone.account.event.update.AccountUpdateEvent;
import com.msa.instagram.clone.account.model.document.AccountMongoDocument;
import com.msa.instagram.clone.account.repository.AccountMongoRepository;
import com.msa.instagram.clone.common.enums.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Slf4j
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Aggregate
@Setter
public class AccountAggregate {


    @AggregateIdentifier
    private String id;

    private String userName;
    private String password;
    private String nickname;
    private boolean isActive;
    private String website;
    private String intro;
    private String email;
    private String telephone;
    private Gender gender;
    private String profileUrl;

    @CommandHandler
    public AccountAggregate(AccountCreateCommand command, AccountMongoRepository accountRepository) {

        log.info("AccountAggregate constructor!!!");

        final List<AccountMongoDocument> accountDocumentList = accountRepository
                .findByUserNamesOrderByTimestampDesc(command.getUserName());

        if(CollectionUtils.isNotEmpty(accountDocumentList) && accountDocumentList.get(0).getEventType() != EventType.DELETE) {
            /*
             * 이벤트 스토어 account 생성 이벤트 전 validation
             * */
            throw new RuntimeException();
        }
        AccountCreateEvent accountCreateEvent = new AccountCreateEvent(command);
        final AccountMongoDocument saveAccountDocument = accountRepository.save(new AccountMongoDocument(accountCreateEvent));
        accountCreateEvent.setId(saveAccountDocument.getId());
        log.info("accountCreateEvent => {}", accountCreateEvent);
        apply(accountCreateEvent);
    }

    @CommandHandler
    public void handle(AccountUpdateCommand command, AccountMongoRepository accountRepository) {
        log.info("account aggregate update handle!!!");
        log.info("acountUpdateCommand => {}", command);
        final List<AccountMongoDocument> accountDocument = accountRepository.findByUserNamesOrderByTimestampAsc(command.getUserName());
        /*
         * command로 들어온 값과 이벤트 스토어에서 데이터를 가져와 replay한 후에 diff!!!
         * 변경 분만 update 하기!!!
         * */
        accountDocument.forEach(item -> replay(item));
        log.info("account Document -> {}", accountDocument);
        /*
        * diff
        * */
        final Optional<AccountUpdateEvent> accountUpdateEventOptional = diff(command);
        if(!accountUpdateEventOptional.isPresent()) {
            throw new RuntimeException("not chnage!!");
        }
        accountRepository.save(new AccountMongoDocument(accountUpdateEventOptional.get()));
        apply(accountUpdateEventOptional.get());
    }

    @EventSourcingHandler
    public void on (AccountCreateEvent event) {
        setByCreateEvent(event);
    }

    @EventSourcingHandler
    public void on (AccountUpdateEvent event) {
        log.info("event => {}", event);
        final AccountMongoDocument accountDocument = new AccountMongoDocument(event);
        log.info("accountDocument => {}", accountDocument);
        updateEventApply(accountDocument);
        log.info("accountAggregate => {}", this);
    }

    public void replay(AccountMongoDocument accountDocument) {
        switch (accountDocument.getEventType()) {
            case CREATE:
                createEventApply(accountDocument);
                break;
            case UPDATE:
                updateEventApply(accountDocument);
                break;
            case DELETE:
                break;
        }
    }

    private void updateEventApply(AccountMongoDocument accountDocument) {
        AccountUpdateEvent accountUpdateEvent = (AccountUpdateEvent) accountDocument.getEvent();
        accountDocument.getAggregateFieldList().forEach(item -> item.replay(this, accountUpdateEvent));
    }

    private void createEventApply(AccountMongoDocument accountDocument) {
        setByCreateEvent((AccountCreateEvent) accountDocument.getEvent());
        this.id = accountDocument.getId();
    }

    private void setByCreateEvent(AccountCreateEvent event) {
        this.id = event.getId();
        this.userName = event.getUserName();
        this.password = event.getPassword();
        this.nickname = event.getNickname();
        this.isActive = event.isActive();
        this.website = event.getWebsite();
        this.intro = event.getIntro();
        this.email = event.getEmail();
        this.telephone = event.getTelephone();
        this.gender = event.getGender();
        this.profileUrl = event.getProfileUrl();
    }

    private Optional<AccountUpdateEvent> diff(AccountUpdateCommand command) {
        final List<AccountAggregateField> aggregateFieldList = new ArrayList<>();
        final AccountUpdateEvent.AccountUpdateEventBuilder accountUpdateEventBuilder = AccountUpdateEvent.builder();
        boolean diffFlag = false;

        if(Objects.nonNull(command.getUserName()) && !StringUtils.equals(this.userName, command.getUserName())) {
            accountUpdateEventBuilder.userName(command.getUserName());
            aggregateFieldList.add(AccountAggregateField.USER_NAME);
            diffFlag = true;
        }
        if(Objects.nonNull(command.getPassword()) && !StringUtils.equals(this.password, command.getPassword())) {
            accountUpdateEventBuilder.password(command.getPassword());
            aggregateFieldList.add(AccountAggregateField.PASSWORD);
            diffFlag = true;
        }
        if(Objects.nonNull(command.getNickname()) && !StringUtils.equals(this.nickname, command.getNickname())) {
            accountUpdateEventBuilder.nickname(command.getNickname());
            aggregateFieldList.add(AccountAggregateField.NINK_NAME);
            diffFlag = true;
        }
        if(this.isActive != command.isActive()) {
            accountUpdateEventBuilder.isActive(command.isActive());
            aggregateFieldList.add(AccountAggregateField.IS_ACTIVE);
            diffFlag = true;
        }
        if(Objects.nonNull(command.getWebsite()) && !StringUtils.equals(this.website, command.getWebsite())) {
            accountUpdateEventBuilder.website(command.getWebsite());
            aggregateFieldList.add(AccountAggregateField.WEB_SITE);
            diffFlag = true;
        }
        if(Objects.nonNull(command.getIntro()) && !StringUtils.equals(this.intro, command.getIntro())) {
            accountUpdateEventBuilder.intro(command.getIntro());
            aggregateFieldList.add(AccountAggregateField.INTRO);
            diffFlag = true;
        }
        if(Objects.nonNull(command.getEmail()) && !StringUtils.equals(this.email, command.getEmail())) {
            accountUpdateEventBuilder.email(command.getEmail());
            aggregateFieldList.add(AccountAggregateField.EMAIL);
            diffFlag = true;
        }
        if(Objects.nonNull(command.getTelephone()) && !StringUtils.equals(this.telephone, command.getTelephone())) {
            accountUpdateEventBuilder.telephone(command.getTelephone());
            aggregateFieldList.add(AccountAggregateField.TELEPHONE);
            diffFlag = true;
        }
        if(Objects.nonNull(command.getGender()) && !StringUtils.equals(this.gender.name(), command.getGender().name())) {
            accountUpdateEventBuilder.gender(command.getGender());
            aggregateFieldList.add(AccountAggregateField.GENDER);
            diffFlag = true;
        }
        if(Objects.nonNull(command.getProfileUrl()) && !StringUtils.equals(this.profileUrl, command.getProfileUrl())) {
            accountUpdateEventBuilder.profileUrl(command.getProfileUrl());
            aggregateFieldList.add(AccountAggregateField.PROFILE_URL);
            diffFlag = true;
        }

        if(diffFlag) {
            accountUpdateEventBuilder.timestamp(command.getTimestamp());
            accountUpdateEventBuilder.accountAggregateFields(aggregateFieldList);
            return Optional.of(accountUpdateEventBuilder.build());
        }
        return Optional.empty();
    }
}
