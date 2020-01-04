package com.msa.instagram.clone.social.like.event;

import com.msa.instagram.clone.social.like.enums.LikeType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UnLikeEvent {
    private final String id;
    private final String targetId;
    private final LikeType likeType;
}
