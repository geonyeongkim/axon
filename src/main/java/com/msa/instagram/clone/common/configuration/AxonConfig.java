package com.msa.instagram.clone.common.configuration;

import com.mongodb.MongoClient;
import com.msa.instagram.clone.account.aggregate.AccountAggregate;
import com.msa.instagram.clone.social.comment.aggregate.CommentAggregate;
import com.msa.instagram.clone.social.post.aggregate.PostAggregate;
import org.axonframework.eventsourcing.*;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {

    @Value("${axon.snapshot.count}")
    private int snapshotCount;

    @Bean
    public EventStorageEngine eventStore(MongoClient mongoClient){
        return new MongoEventStorageEngine(new DefaultMongoTemplate(mongoClient));
    }

    @Bean
    public Snapshotter snapShotter(EventStore eventStore){
        return new AggregateSnapshotter(
                eventStore,
                new GenericAggregateFactory<>(AccountAggregate.class),
                new GenericAggregateFactory<>(PostAggregate.class),
                new GenericAggregateFactory<>(CommentAggregate.class)
//                new GenericAggregateFactory<>(CommentAggregate.class)
        );
    }

    @Bean
    public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
        return new EventCountSnapshotTriggerDefinition(snapshotter, snapshotCount);
    }

    @Bean
    public EventSourcingRepository<AccountAggregate> aggregateEventSourcingRepository(EventStore eventStore, SnapshotTriggerDefinition snapshotTriggerDefinition) {
        return new EventSourcingRepository<>(AccountAggregate.class, eventStore, snapshotTriggerDefinition);
    }
}
