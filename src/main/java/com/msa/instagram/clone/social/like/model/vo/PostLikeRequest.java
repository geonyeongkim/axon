package com.msa.instagram.clone.social.like.model.vo;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PostLikeRequest extends LikeRequest{
    private String postId;
}
