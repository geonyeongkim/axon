package com.msa.instagram.clone.social.comment.model.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentCreateRequest {
    private String authorId;
    private String postId;
    private String content;
}
