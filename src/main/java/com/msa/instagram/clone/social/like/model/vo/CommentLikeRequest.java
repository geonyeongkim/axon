package com.msa.instagram.clone.social.like.model.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CommentLikeRequest extends LikeRequest{
    private String commentId;
}
