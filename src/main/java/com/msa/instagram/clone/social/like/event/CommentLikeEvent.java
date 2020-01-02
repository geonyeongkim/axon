package com.msa.instagram.clone.social.like.event;

import com.msa.instagram.clone.social.like.command.CommentLikeCommand;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentLikeEvent {
    private String id;
    private String authorId;
    private String commentId;

    public CommentLikeEvent(CommentLikeCommand command) {
        this.id = command.getId();
        this.authorId = command.getAuthorId();
        this.commentId = command.getCommentId();
    }
}
