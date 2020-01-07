package com.msa.instagram.clone.test;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

/**
 * Created by geonyeong.kim on 2020-01-07
 */
@Slf4j
@NoArgsConstructor
@Aggregate(repository = "testAggregateEventSourcingRepository")
public class TestAggregate {

    @AggregateIdentifier
    private String id;

    @CommandHandler
    public TestAggregate(TestCommand command) {
        log.info("TestCommand => {}", command);
        apply(new TestEvent(command.getId()));

    }

    @EventSourcingHandler
    public void on(TestEvent event) {
        log.info("TestEvent => {}", event);
        this.id = event.getId();
    }

}
