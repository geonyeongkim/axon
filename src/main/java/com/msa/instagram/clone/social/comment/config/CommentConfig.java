package com.msa.instagram.clone.social.comment.config;

import com.msa.instagram.clone.social.comment.aggregate.CommentAggregate;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommentConfig {
    @Value("${axon.snapshot.count}")
    private int snapshotCount;

    @Bean
    public Snapshotter commentSnapShotter(EventStore eventStore) {
        return AggregateSnapshotter
                .builder()
                .eventStore(eventStore)
                .aggregateFactories(new GenericAggregateFactory<>(CommentAggregate.class))
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition commentSnapshotTriggerDefinition(Snapshotter commentSnapShotter) {
        return new EventCountSnapshotTriggerDefinition(commentSnapShotter, snapshotCount);
    }

    @Bean
    public EventSourcingRepository<CommentAggregate> commentAggregateEventSourcingRepository(EventStore eventStore, SnapshotTriggerDefinition commentSnapshotTriggerDefinition) {
        return EventSourcingRepository.builder(CommentAggregate.class).eventStore(eventStore).snapshotTriggerDefinition(commentSnapshotTriggerDefinition).build();
    }
}
