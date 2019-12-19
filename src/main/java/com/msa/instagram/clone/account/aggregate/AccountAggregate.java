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
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Aggregate
@AggregateRoot
public class AccountAggregate {

    @AggregateIdentifier
    private String id;

    @CommandHandler
    public void handle(SignUpCommand signUpCommand) {
        // TODO: logging
        log.info("AccountAggregate handle!!!");
//        SignUpRequest signUpRequest = signUpCommand.getSignUpRequest();
//        log.info("signUpRequest => {}", signUpRequest );
//        apply(SignUpEvent.builder()
//                .signUpRequest(signUpRequest)
//                .timestamp(System.currentTimeMillis())
//        );
    }

    @EventSourcingHandler
    public void on(SignUpEvent signUpEvent) {
        log.info("AccountAggregate on!!");
        log.info("signUpEvent => {}", signUpEvent);
        // TODO: 이벤트 스토어 저장.
    }
}
