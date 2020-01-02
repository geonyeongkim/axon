package com.msa.instagram.clone.social.post.model.vo;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PostCreateRequest {

    private String authorId;
    private String content;
    private List<Media> mediaList;
}
