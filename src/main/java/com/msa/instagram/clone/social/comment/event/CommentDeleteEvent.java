package com.msa.instagram.clone.social.comment.event;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentDeleteEvent extends CommentEvent {
    public CommentDeleteEvent(String id, String postId) {
        this.id = id;
        this.postId = postId;
    }
}
