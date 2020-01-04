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
        return new AggregateSnapshotter(
                eventStore,
                new GenericAggregateFactory<>(LikeAggregate.class)
        );
    }

    @Bean
    public SnapshotTriggerDefinition likeSnapshotTriggerDefinition(Snapshotter likeSnapShotter) {
        return new EventCountSnapshotTriggerDefinition(likeSnapShotter, snapshotCount);
    }

    @Bean
    public EventSourcingRepository<LikeAggregate> likeAggregateEventSourcingRepository(EventStore eventStore, SnapshotTriggerDefinition likeSnapshotTriggerDefinition) {
        return new EventSourcingRepository<>(LikeAggregate.class, eventStore, likeSnapshotTriggerDefinition);
    }
}
