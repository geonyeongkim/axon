package com.msa.instagram.clone.account.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.command.AccountUpdateCommand;
import com.msa.instagram.clone.account.enums.Gender;
import com.msa.instagram.clone.account.event.create.AccountCreateEvent;
import com.msa.instagram.clone.account.event.update.AccountUpdateEvent;
import com.msa.instagram.clone.account.model.document.AccountDocument;
import com.msa.instagram.clone.account.repository.AccountRepository;
import com.msa.instagram.clone.common.enums.EventType;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
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
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    private String userName;
    private String password;
    private String ninkname;
    private boolean isActive;
    private String website;
    private String intro;
    private String email;
    private String telephone;
    private Gender gender;
    private String profileUrl;

    @CommandHandler
    public AccountAggregate(AccountCreateCommand command, AccountRepository accountRepository) {

        log.info("AccountAggregate constructor!!!");

        final List<AccountDocument> accountDocumentList = accountRepository
                .findByUserNamesOrderOrderByTimestampDesc(command.getUserName());

        if(CollectionUtils.isNotEmpty(accountDocumentList) && accountDocumentList.get(0).getEventType() != EventType.DELETE) {
            /*
             * 이벤트 스토어 account 생성 이벤트 전 validation
             * */
            throw new RuntimeException();
        }
        AccountCreateEvent accountCreateEvent = new AccountCreateEvent(command);
        final AccountDocument saveAccountDocument = accountRepository.save(new AccountDocument(accountCreateEvent));
        accountCreateEvent.setId(saveAccountDocument.getId());
        log.info("accountCreateEvent => {}", accountCreateEvent);
        apply(accountCreateEvent);
    }

    @CommandHandler
    public void handle(AccountUpdateCommand command, AccountRepository accountRepository) {
        final List<AccountDocument> accountDocument = accountRepository.findByUserNamesOrderOrderByTimestampAsc(command.getUserName());
        /*
         * command로 들어온 값과 이벤트 스토어에서 데이터를 가져와 replay한 후에 diff!!!
         * 변경 분만 update 하기!!!
         * */
        accountDocument.forEach(item -> replay(item));

        // diff


        apply(new AccountUpdateEvent());
    }

    @EventSourcingHandler
    public void on (AccountCreateEvent event) {
        setByCreateEvent(event);
    }

    @EventSourcingHandler
    public void on (AccountUpdateEvent event) {

    }

    private void replay(AccountDocument accountDocument) {
        switch (accountDocument.getEventType()) {
            case CREATE:
                createEventApply(accountDocument);
                break;
            case UPDATE:
                break;
            case DELETE:
                break;
        }
    }

    private void createEventApply(AccountDocument accountDocument) {
        setByCreateEvent((AccountCreateEvent) accountDocument.getEvent());
        this.id = accountDocument.getId();
    }

    private void setByCreateEvent(AccountCreateEvent event) {
        this.id = event.getId();
        this.userName = event.getUserName();
        this.password = event.getPassword();
        this.ninkname = event.getNinkname();
        this.isActive = event.isActive();
        this.website = event.getWebsite();
        this.intro = event.getIntro();
        this.email = event.getEmail();
        this.telephone = event.getTelephone();
        this.gender = event.getGender();
        this.profileUrl = event.getProfileUrl();
    }
}
