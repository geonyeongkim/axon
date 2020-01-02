package com.msa.instagram.clone.social.like.event;

import com.msa.instagram.clone.social.like.command.PostLikeCommand;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostLikeEvent {
    private String id;
    private String authorId;
    private String postId;

    public PostLikeEvent(PostLikeCommand command) {
        this.id = command.getId();
        this.authorId = command.getAuthorId();
        this.postId = command.getPostId();
    }
}
