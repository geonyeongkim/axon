package com.msa.instagram.clone.social.post.model.document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.msa.instagram.clone.social.like.event.CommentLikeEvent;
import com.msa.instagram.clone.social.like.event.PostLikeEvent;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LikeEs {
    private String id;
    private String postId;
    private String commentId;
    private String authorId;;

    public LikeEs(PostLikeEvent event) {
        this.id = event.getId();
        this.postId = event.getPostId();
        this.authorId = event.getAuthorId();
    }

    public LikeEs(CommentLikeEvent event) {
        this.id = event.getId();
        this.commentId = event.getCommentId();
        this.authorId = event.getAuthorId();
    }
}
