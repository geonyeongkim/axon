package com.msa.instagram.clone.social.comment.listener;

import com.msa.instagram.clone.social.comment.event.CommentCreateEvent;
import com.msa.instagram.clone.social.comment.event.CommentDeleteEvent;
import com.msa.instagram.clone.social.comment.event.CommentEvent;
import com.msa.instagram.clone.social.comment.event.CommentUpdateEvent;
import com.msa.instagram.clone.social.post.model.document.CommentEs;
import com.msa.instagram.clone.social.post.model.document.PostEsDocument;
import com.msa.instagram.clone.social.post.repository.PostEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class CommentEventListener {

    private final PostEsRepository postEsRepository;

    @EventHandler
    public void handle(CommentCreateEvent event) {
        final PostEsDocument postEsDocument = postEsRepository.findById(event.getPostId()).orElseThrow();
        postEsDocument.getComments().add(new CommentEs(event));
        postEsRepository.save(postEsDocument);
    }

    @EventHandler
    public void handle(CommentUpdateEvent event) {
        final Consumer<CommentEs> commentEsConsumer = commentEs -> {
            commentEs.setContent(event.getContent());
        };
        updateComment(event, commentEsConsumer);
    }

    @EventHandler
    public void handle(CommentDeleteEvent event) {
        final Consumer<CommentEs> commentEsConsumer = commentEs -> {
            commentEs.setIsActive(false);
        };
        updateComment(event, commentEsConsumer);
    }

    private void updateComment(CommentEvent event, Consumer<CommentEs> consumer) {
        PostEsDocument postEsDocument = postEsRepository.findById(event.getPostId()).orElseThrow();
        final List<CommentEs> updateCommentEsList = postEsDocument.getComments()
                .stream()
                .map(item -> {
                    if (StringUtils.equals(item.getId(), event.getId())) {
                        consumer.accept(item);
                    }
                    return item;
                }).collect(Collectors.toList());
        postEsDocument.setComments(updateCommentEsList);
        postEsRepository.save(postEsDocument);
    }
}
