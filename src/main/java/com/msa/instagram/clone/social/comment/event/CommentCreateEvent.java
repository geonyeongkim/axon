package com.msa.instagram.clone.social.comment.event;

import com.msa.instagram.clone.social.comment.command.CommentCreateCommand;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentCreateEvent extends CommentEvent {

    private String authorId;
    private String content;
    private boolean isActive;
    private long timestamp;

    public CommentCreateEvent(CommentCreateCommand command) {
        this.id = command.getId();
        this.authorId = command.getAuthorId();
        this.postId = command.getPostId();
        this.content = command.getContent();
        this.isActive = true;
        this.timestamp = command.getTimestamp();
    }
}
