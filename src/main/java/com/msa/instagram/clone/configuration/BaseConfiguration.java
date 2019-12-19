package com.msa.instagram.clone.configuration;

import com.msa.instagram.clone.account.aggregate.AccountAggregate;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.axonserver.connector.event.axon.AxonServerEventStore;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.modelling.command.Repository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BaseConfiguration implements InitializingBean {

    @Bean
    public Repository<AccountAggregate> aggregateRepository(EventStore eventStore, Cache cache) {
        return EventSourcingRepository.builder(AccountAggregate.class)
                .cache(cache)
                .eventStore(eventStore)
                .build();
    }

    @Bean
    public Cache cache() {
        return new WeakReferenceCache();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("configuration bean create!!!");
    }
}
