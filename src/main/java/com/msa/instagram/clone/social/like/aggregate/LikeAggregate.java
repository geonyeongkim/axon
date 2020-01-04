package com.msa.instagram.clone.social.like.aggregate;

import com.msa.instagram.clone.social.like.command.CommentLikeCommand;
import com.msa.instagram.clone.social.like.command.PostLikeCommand;
import com.msa.instagram.clone.social.like.command.UnLikeCommand;
import com.msa.instagram.clone.social.like.event.CommentLikeEvent;
import com.msa.instagram.clone.social.like.event.PostLikeEvent;
import com.msa.instagram.clone.social.like.event.UnLikeEvent;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;
import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

@Slf4j
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Aggregate(repository = "likeAggregateEventSourcingRepository")
public class LikeAggregate {

    @AggregateIdentifier
    private String id;
    private String authorId;
    private String postId;
    private String commentId;

    @CommandHandler
    public LikeAggregate(PostLikeCommand command) {
        apply(new PostLikeEvent(command));
    }

    @CommandHandler
    public LikeAggregate(CommentLikeCommand command) {
        apply(new CommentLikeEvent(command));
    }

    @CommandHandler
    public void handle(UnLikeCommand command) {
        apply(new UnLikeEvent());
    }

    @EventSourcingHandler
    public void on(PostLikeEvent event) {
        this.id = event.getId();
        this.postId = event.getPostId();
        this.authorId = event.getAuthorId();
    }

    @EventSourcingHandler
    public void on(CommentLikeEvent event) {
        this.id = event.getId();
        this.commentId = event.getCommentId();
        this.authorId = event.getAuthorId();
    }

    @EventSourcingHandler
    public void on(UnLikeEvent event) {
        this.id = null;
    }
}
