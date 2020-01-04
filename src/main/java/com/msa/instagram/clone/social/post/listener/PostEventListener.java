package com.msa.instagram.clone.social.post.listener;

import com.msa.instagram.clone.common.EventListener;
import com.msa.instagram.clone.social.post.enums.PostEsField;
import com.msa.instagram.clone.social.post.event.PostCreateEvent;
import com.msa.instagram.clone.social.post.event.PostDeleteEvent;
import com.msa.instagram.clone.social.post.event.PostUpdateEvent;
import com.msa.instagram.clone.social.post.model.document.PostEsDocument;
import com.msa.instagram.clone.social.post.repository.PostEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class PostEventListener extends EventListener {

    private final PostEsRepository postEsRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    @EventHandler
    public void handle(PostCreateEvent event) {
        final PostEsDocument postEsDocument = new PostEsDocument(event);
        postEsRepository.save(postEsDocument);
    }

    @EventHandler
    public void handle(PostUpdateEvent event) {
        log.info("PostUpdateEvent => {}", event);
        final Map updateMap = event.getPostAggregateFieldList()
                .stream()
                .collect(
                        Collectors.toMap(
                                item -> PostEsField.getPostEsFieldByPostAggregateField(item).getEsFieldName(),
                                item -> item.getGetExpress().apply(event)
                        )
                );
        log.info("post event listener updateMap => {}", updateMap);
        elasticsearchTemplate.update(makeUpdateQuery(event.getId(), updateMap, PostEsDocument.class));
    }

    @EventHandler
    public void handle(PostDeleteEvent event) {
        elasticsearchTemplate.update(makeUpdateQuery(
                event.getId(),
                new HashMap(){{put(PostEsField.getIsActiveFieldName(), false);}},
                PostEsDocument.class)
        );
    }
}
