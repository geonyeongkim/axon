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
        return AggregateSnapshotter.builder()
                .eventStore(eventStore)
                .aggregateFactories(new GenericAggregateFactory<>(AccountAggregate.class))
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition accountSnapshotTriggerDefinition(Snapshotter accountSnapShotter) {
        return new EventCountSnapshotTriggerDefinition(accountSnapShotter, snapshotCount);
    }

    @Bean
    public EventSourcingRepository<AccountAggregate> accountAggregateEventSourcingRepository(EventStore eventStore, SnapshotTriggerDefinition accountSnapshotTriggerDefinition) {
        return EventSourcingRepository.builder(AccountAggregate.class).eventStore(eventStore).snapshotTriggerDefinition(accountSnapshotTriggerDefinition).build();
    }
}
