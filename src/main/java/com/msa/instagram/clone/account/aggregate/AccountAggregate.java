package com.msa.instagram.clone.account.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.msa.instagram.clone.account.command.SignUpCommand;
import com.msa.instagram.clone.account.event.SignUpEvent;
import com.msa.instagram.clone.account.model.vo.SignUpRequest;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Slf4j
@Aggregate
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    @CommandHandler
    public void handle(SignUpCommand signUpCommand) {
        // TODO: logging
        SignUpRequest signUpRequest = signUpCommand.getSignUpRequest();
        apply(SignUpEvent.builder()
                .signUpRequest(signUpRequest)
                .timestamp(System.currentTimeMillis())
        );
    }

    @EventSourcingHandler
    public void on(SignUpEvent signUpEvent) {
        log.info("receive signUpEvent!!");

        // TODO: 이벤트 스토어 저장.

    }
}
