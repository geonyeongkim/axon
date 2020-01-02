package com.msa.instagram.clone.social.post.enums;

import com.msa.instagram.clone.social.post.aggregate.PostAggregate;
import com.msa.instagram.clone.social.post.event.PostUpdateEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.util.Pair;

import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum PostAggregateField {

    CONTENT(postUpdateEvent -> postUpdateEvent.getContent(), pair -> pair.getFirst().setContent(String.valueOf(pair.getSecond()))),
    UPDATE_TIME(postUpdateEvent -> postUpdateEvent.getUpdateTimestamp(), pair -> pair.getFirst().setUpdateTimestamp((long) pair.getSecond()));
    private Function<PostUpdateEvent, Object> getExpress;
    private Consumer<Pair<PostAggregate, Object>> setExpress;

    public void updatePostAggregate(PostAggregate postAggregate, PostUpdateEvent postUpdateEvent) {
        setExpress.accept(Pair.of(postAggregate, getExpress.apply(postUpdateEvent)));
    }
}
