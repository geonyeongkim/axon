package com.msa.instagram.clone.social.comment.model.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentUpdateRequest {
    private String id;
    private String content;
}
