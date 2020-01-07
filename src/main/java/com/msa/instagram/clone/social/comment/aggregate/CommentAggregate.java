package com.msa.instagram.clone.social.comment.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import com.msa.instagram.clone.common.aggregate.CommonAggregate;
import com.msa.instagram.clone.social.comment.command.CommentCreateCommand;
import com.msa.instagram.clone.social.comment.command.CommentDeleteCommand;
import com.msa.instagram.clone.social.comment.command.CommentUpdateCommand;
import com.msa.instagram.clone.social.comment.enums.CommentAggregateField;
import com.msa.instagram.clone.social.comment.event.CommentCreateEvent;
import com.msa.instagram.clone.social.comment.event.CommentDeleteEvent;
import com.msa.instagram.clone.social.comment.event.CommentUpdateEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

@Slf4j
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Aggregate(repository = "commentAggregateEventSourcingRepository")
public class CommentAggregate extends CommonAggregate<CommentUpdateEvent, CommentUpdateCommand> {

    private String authorId;
    private String postId;
    private String content;
    private boolean isActive;
    private long timestamp;

    @CommandHandler
    public CommentAggregate(CommentCreateCommand command) {
        log.info("CommentCreateCommand => {}", command);
        if(this.isActive) {
            throw new RuntimeException("already comment exist!!");
        }
        apply(new CommentCreateEvent(command));
    }

    @CommandHandler
    public void handle(CommentUpdateCommand command) {
        final Optional<CommentUpdateEvent> commentUpdateEventOptional = diff(command);
        if(!commentUpdateEventOptional.isPresent()) {
            throw new RuntimeException("not chnage!!");
        }
        apply(commentUpdateEventOptional.get());
    }

    @CommandHandler
    public void handle(CommentDeleteCommand command) {
        if(!this.isActive) {
            throw new RuntimeException("already comment not exist!!");
        }
        apply(new CommentDeleteEvent(command.getId(), this.postId));
    }

    @EventSourcingHandler
    public void on(CommentCreateEvent event) {
        this.setId(event.getId());
        this.authorId = event.getAuthorId();
        this.postId = event.getPostId();
        this.content = event.getContent();
        this.isActive = event.isActive();
        this.timestamp = event.getTimestamp();
    }

    @EventSourcingHandler
    public void handle(CommentUpdateEvent event) {
        event.getCommentAggregateFields().forEach(item -> item.updateAggregate(this, event));
    }

    @EventSourcingHandler
    public void handle(CommentDeleteEvent event) {
        this.isActive = false;

    }

    protected Optional<CommentUpdateEvent> diff(CommentUpdateCommand command) {
        final List<CommentAggregateField> aggregateFields = new ArrayList<>();
        final CommentUpdateEvent.CommentUpdateEventBuilder commentUpdateEventBuilder = CommentUpdateEvent
                .builder()
                .id(command.getId())
                .postId(this.postId);
        boolean diffFlag = false;

        if (Objects.nonNull(command.getContent()) && !StringUtils.equals(this.content, command.getContent())) {
            commentUpdateEventBuilder.content(command.getContent());
            aggregateFields.add(CommentAggregateField.CONTENT);
            diffFlag = true;
        }
        if(diffFlag) {
            commentUpdateEventBuilder.commentAggregateFields(aggregateFields);
            return Optional.of(commentUpdateEventBuilder.build());
        }
        return Optional.empty();
    }
}
