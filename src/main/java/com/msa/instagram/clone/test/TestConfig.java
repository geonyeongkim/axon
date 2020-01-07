package com.msa.instagram.clone.test;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by geonyeong.kim on 2020-01-07
 */
@Slf4j
@Configuration
public class TestConfig {

    @Value("${axon.snapshot.count}")
    private int snapshotCount;

    @Bean
    public Snapshotter testSnapShotter(EventStore eventStore){
        return AggregateSnapshotter.builder()
                .eventStore(eventStore)
                .aggregateFactories(new GenericAggregateFactory<>(TestAggregate.class))
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition testSnapshotTriggerDefinition(Snapshotter testSnapShotter) {
        return new EventCountSnapshotTriggerDefinition(testSnapShotter, snapshotCount);
    }

    @Bean
    public EventSourcingRepository<TestAggregate> testAggregateEventSourcingRepository(EventStore eventStore, SnapshotTriggerDefinition testSnapshotTriggerDefinition) {
        return EventSourcingRepository.builder(TestAggregate.class).eventStore(eventStore).snapshotTriggerDefinition(testSnapshotTriggerDefinition).build();
    }
}
