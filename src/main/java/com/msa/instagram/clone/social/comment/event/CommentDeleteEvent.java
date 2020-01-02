package com.msa.instagram.clone.social.comment.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@RequiredArgsConstructor
@ToString
public class CommentDeleteEvent {
    private final String id;
}
