package com.msa.instagram.clone.social.post.listener;

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

@Slf4j
@RequiredArgsConstructor
@Component
public class PostEventListener {

    private final PostEsRepository postEsRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    @EventHandler
    public void handle(PostCreateEvent event) {
        log.info("PostCreateEvent => {}", event);
        final PostEsDocument postEsDocument = new PostEsDocument(event);
        log.info("postEsDocument => {}", postEsDocument);
        postEsRepository.save(postEsDocument);
    }

    @EventHandler
    public void handle(PostUpdateEvent event) {

    }

    @EventHandler
    public void handle(PostDeleteEvent event) {

    }
}
