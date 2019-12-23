package com.msa.instagram.clone.account.aggregate;

import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.enums.Gender;
import com.msa.instagram.clone.account.event.AccountCreateEvent;
import com.msa.instagram.clone.account.model.document.AccountDocument;
import com.msa.instagram.clone.account.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Aggregate
public class AccountAggregate {

    private AccountRepository accountRepository;

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
    private Gender geonder;
    private String profileUrl;

    @CommandHandler
    public AccountAggregate(AccountCreateCommand command, AccountRepository accountRepository) {
        /*
        * 이벤트 스토어 username으로 조회하여 없는지 체크
        * 있더라도 최신 상태가 delete 이벤트라면 적용.
        * */
        this.accountRepository = accountRepository;
        apply(new AccountCreateEvent(command));
    }

    @EventSourcingHandler
    public void on (AccountCreateEvent event) {
        /*
        * 이벤트 스토어 추가
        * */
        log.info("accountRepository => {}", accountRepository);
        final AccountDocument accountDocument = new AccountDocument(event);
        log.info("accountDocument  => {}", accountDocument);
        AccountDocument saveAccountDocument = accountRepository.save(accountDocument);
        this.id = saveAccountDocument.getId();
        log.info("id => {}", id);
    }
}
