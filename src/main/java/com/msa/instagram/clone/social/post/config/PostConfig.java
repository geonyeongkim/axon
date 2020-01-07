package com.msa.instagram.clone.social.post.config;

import com.msa.instagram.clone.social.post.aggregate.PostAggregate;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfig {
    @Value("${axon.snapshot.count}")
    private int snapshotCount;

    @Bean
    public Snapshotter postSnapShotter(EventStore eventStore) {
        return AggregateSnapshotter
                .builder()
                .eventStore(eventStore)
                .aggregateFactories(new GenericAggregateFactory<>(PostAggregate.class))
                .build();
    }

    @Bean
    public SnapshotTriggerDefinition postSnapshotTriggerDefinition(Snapshotter postSnapShotter) {
        return new EventCountSnapshotTriggerDefinition(postSnapShotter, snapshotCount);
    }

    @Bean
    public EventSourcingRepository<PostAggregate> postAggregateEventSourcingRepository(EventStore eventStore, SnapshotTriggerDefinition postSnapshotTriggerDefinition) {
        return EventSourcingRepository.builder(PostAggregate.class).eventStore(eventStore).snapshotTriggerDefinition(postSnapshotTriggerDefinition).build();
    }
}
