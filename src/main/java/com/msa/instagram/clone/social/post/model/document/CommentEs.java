package com.msa.instagram.clone.social.post.model.document;

import com.msa.instagram.clone.social.comment.event.CommentCreateEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CommentEs {

    private String id;
    private String authorId;
    private String postId;
    private String content;
    private Boolean isActive;
    private List<LikeEs> likes;
    private long timestamp;

    public CommentEs(CommentCreateEvent event) {
        this.id = event.getId();
        this.authorId = event.getAuthorId();
        this.postId = event.getPostId();
        this.content = event.getContent();
        this.isActive = event.isActive();
        this.timestamp = event.getTimestamp();
        this.likes = new ArrayList<>();
    }
}
