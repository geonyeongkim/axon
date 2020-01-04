package com.msa.instagram.clone.account.config;

import com.msa.instagram.clone.account.aggregate.AccountAggregate;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccountConfig {

    @Value("${axon.snapshot.count}")
    private int snapshotCount;

    @Bean
    public Snapshotter accountSnapShotter(EventStore eventStore){
        return new AggregateSnapshotter(
                eventStore,
                new GenericAggregateFactory<>(AccountAggregate.class)
        );
    }

    @Bean
    public SnapshotTriggerDefinition accountSnapshotTriggerDefinition(Snapshotter accountSnapShotter) {
        return new EventCountSnapshotTriggerDefinition(accountSnapShotter, snapshotCount);
    }

    @Bean
    public EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository(EventStore eventStore, SnapshotTriggerDefinition accountSnapshotTriggerDefinition) {
        return new EventSourcingRepository<>(AccountAggregate.class, eventStore, accountSnapshotTriggerDefinition);
    }
}
