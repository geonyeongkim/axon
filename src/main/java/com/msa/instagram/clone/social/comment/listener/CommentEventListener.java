package com.msa.instagram.clone.social.comment.listener;

import com.msa.instagram.clone.social.comment.event.CommentCreateEvent;
import com.msa.instagram.clone.social.comment.event.CommentDeleteEvent;
import com.msa.instagram.clone.social.comment.event.CommentUpdateEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommentEventListener {

    private final ElasticsearchTemplate elasticsearchTemplate;

    @EventHandler
    public void handle(CommentCreateEvent event) {

    }

    @EventHandler
    public void handle(CommentUpdateEvent event) {

    }

    @EventHandler
    public void handle(CommentDeleteEvent event) {

    }
}
