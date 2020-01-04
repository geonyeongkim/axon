package com.msa.instagram.clone.common;

import org.elasticsearch.action.index.IndexRequest;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;

import java.util.Map;

public class EventListener {

    protected UpdateQuery makeUpdateQuery(String id, Map map, Class<?> clazz) {
        final IndexRequest indexRequest = new IndexRequest();
        indexRequest.source(map);
        return new UpdateQueryBuilder().withId(id).withClass(clazz).withIndexRequest(indexRequest).build();
    }
}
