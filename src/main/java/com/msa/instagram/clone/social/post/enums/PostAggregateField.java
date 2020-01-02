package com.msa.instagram.clone.social.post.enums;

import com.msa.instagram.clone.common.support.AggregateField;
import com.msa.instagram.clone.social.post.aggregate.PostAggregate;
import com.msa.instagram.clone.social.post.event.PostUpdateEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum PostAggregateField implements AggregateField<PostAggregate, PostUpdateEvent> {

    CONTENT(postUpdateEvent -> postUpdateEvent.getContent(), pair -> pair.getFirst().setContent(String.valueOf(pair.getSecond()))),
    UPDATE_TIME(postUpdateEvent -> postUpdateEvent.getUpdateTimestamp(), pair -> pair.getFirst().setUpdateTimestamp((long) pair.getSecond()))
    ;

    private final Function<PostUpdateEvent, Object> getExpress;
    private final Consumer<Pair<PostAggregate, Object>> setExpress;

    @Override
    public void updateAggregate(PostAggregate aggregate, PostUpdateEvent event) {
        setExpress.accept(Pair.of(aggregate, getExpress.apply(event)));
    }
}
