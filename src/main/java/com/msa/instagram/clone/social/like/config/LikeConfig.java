package com.msa.instagram.clone.social.like.config;

import com.msa.instagram.clone.social.like.aggregate.LikeAggregate;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LikeConfig {
    @Value("${axon.snapshot.count}")
    private int snapshotCount;

    @Bean
    public Snapshotter likeSnapShotter(EventStore eventStore) {
        return AggregateSnapshotter
                .builder()
                .eventStore(eventStore)
                .aggregateFactories(new GenericAggregateFactory<>(LikeAggregate.class))
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition likeSnapshotTriggerDefinition(Snapshotter likeSnapShotter) {
        return new EventCountSnapshotTriggerDefinition(likeSnapShotter, snapshotCount);
    }

    @Bean
    public EventSourcingRepository<LikeAggregate> likeAggregateEventSourcingRepository(EventStore eventStore, SnapshotTriggerDefinition likeSnapshotTriggerDefinition) {
        return EventSourcingRepository.builder(LikeAggregate.class).eventStore(eventStore).snapshotTriggerDefinition(likeSnapshotTriggerDefinition).build();
    }
}
