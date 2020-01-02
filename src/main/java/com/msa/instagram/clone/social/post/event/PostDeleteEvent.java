package com.msa.instagram.clone.social.post.event;

import lombok.*;

@Getter
@RequiredArgsConstructor
@ToString
public class PostDeleteEvent {
    private final String id;
}
