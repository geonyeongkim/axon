package com.msa.instagram.clone.account.aggregate;

import com.msa.instagram.clone.account.command.SignUpCommand;
import com.msa.instagram.clone.account.event.SignUpEvent;
import com.msa.instagram.clone.account.model.vo.SignUpRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateRoot;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Slf4j
@Aggregate
@AggregateRoot
public class AccountAggregate {

    @AggregateIdentifier
    private Integer accountAggregateId;

    private String name;

    protected AccountAggregate() {
    }

    @CommandHandler
    public AccountAggregate(SignUpCommand signUpCommand) {
        log.info("AccountAggregate handle!!!");
        apply(new SignUpEvent(signUpCommand.getName(), System.currentTimeMillis()));
    }

    @EventSourcingHandler
    public void on(SignUpEvent signUpEvent) {
        log.info("AccountAggregate on!!");
        log.info("signUpEvent => {}", signUpEvent);

        // TODO: 이벤트 스토어 저장.
    }
}
