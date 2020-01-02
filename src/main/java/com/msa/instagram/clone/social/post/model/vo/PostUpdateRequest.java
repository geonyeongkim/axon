package com.msa.instagram.clone.social.post.model.vo;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PostUpdateRequest {

    private String id;
    private String authorId;
    private String content;
}
