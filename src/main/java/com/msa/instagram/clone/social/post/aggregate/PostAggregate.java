package com.msa.instagram.clone.social.post.aggregate;

import com.msa.instagram.clone.account.enums.AccountAggregateField;
import com.msa.instagram.clone.account.event.AccountUpdateEvent;
import com.msa.instagram.clone.social.post.command.PostCreateCommand;
import com.msa.instagram.clone.social.post.command.PostDeleteCommand;
import com.msa.instagram.clone.social.post.command.PostUpdateCommand;
import com.msa.instagram.clone.social.post.enums.PostAggregateField;
import com.msa.instagram.clone.social.post.event.PostCreateEvent;
import com.msa.instagram.clone.social.post.event.PostDeleteEvent;
import com.msa.instagram.clone.social.post.event.PostUpdateEvent;
import com.msa.instagram.clone.social.post.model.vo.Media;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Setter
@Aggregate
public class PostAggregate {

    @AggregateIdentifier
    private String id;

    private String authorId;
    private String content;
    private List<Media> mediaList;
    private boolean isActive;
    private long createTimestamp;
    private long updateTimestamp;

    @CommandHandler
    public PostAggregate(PostCreateCommand command) {
        // TODO: media 업로드
        if (this.isActive) {
            throw new RuntimeException("already post exist!!");
        }
        apply(new PostCreateEvent(command));
    }

    @CommandHandler
    public void handle(PostUpdateCommand command) {
        final Optional<PostUpdateEvent> postUpdateEventOptional = diff(command);
        if(!postUpdateEventOptional.isPresent()) {
            throw new RuntimeException("not chnage!!");
        }
        apply(postUpdateEventOptional.get());
    }

    @CommandHandler
    public void handle(PostDeleteCommand command) {
        if (!this.isActive) {
            throw new RuntimeException("already post not exist!!");
        }
        apply(new PostDeleteEvent(command.getId()));

    }

    @EventSourcingHandler
    public void on(PostCreateEvent event) {
        this.id = event.getId();
        this.authorId = event.getAuthorId();
        this.content = event.getContent();
        this.mediaList = event.getMediaList();
        this.isActive = event.isActive();
        this.createTimestamp = event.getCreateTimestamp();
        this.updateTimestamp = event.getUpdateTimestamp();
    }

    @EventSourcingHandler
    public void on(PostUpdateEvent event) {
        event.getPostAggregateFieldList().forEach(item -> item.updatePostAggregate(this, event));
    }

    @EventSourcingHandler
    public void on(PostDeleteEvent event) {
        this.isActive = false;
    }

    private Optional<PostUpdateEvent> diff(PostUpdateCommand command) {
        final List<PostAggregateField> aggregateFields = new ArrayList<>();
        final PostUpdateEvent.PostUpdateEventBuilder postUpdateEventBuilder = PostUpdateEvent
                .builder()
                .id(command.getId());
        boolean diffFlag = false;

        if (Objects.nonNull(command.getContent()) && !StringUtils.equals(this.content, command.getContent())) {
            postUpdateEventBuilder.content(command.getContent());
            aggregateFields.add(PostAggregateField.CONTENT);
            diffFlag = true;
        }

        if(diffFlag) {
            postUpdateEventBuilder.updateTimestamp(command.getUpdateTimestamp());
            aggregateFields.add(PostAggregateField.UPDATE_TIME);
            postUpdateEventBuilder.postAggregateFieldList(aggregateFields);
            return Optional.of(postUpdateEventBuilder.build());
        }
        return Optional.empty();
    }
}
