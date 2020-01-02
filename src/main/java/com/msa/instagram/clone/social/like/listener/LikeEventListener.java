package com.msa.instagram.clone.social.like.listener;

import com.msa.instagram.clone.social.like.event.CommentLikeEvent;
import com.msa.instagram.clone.social.like.event.PostLikeEvent;
import com.msa.instagram.clone.social.like.event.UnLikeEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class LikeEventListener {

    private final ElasticsearchTemplate elasticsearchTemplate;

    @EventHandler
    public void on (PostLikeEvent event) {

    }

    @EventHandler
    public void on (CommentLikeEvent event) {

    }

    @EventHandler
    public void on (UnLikeEvent event) {

    }
}
