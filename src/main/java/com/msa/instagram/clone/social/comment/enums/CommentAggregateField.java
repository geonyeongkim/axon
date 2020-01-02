package com.msa.instagram.clone.social.comment.enums;

import com.msa.instagram.clone.common.support.AggregateField;
import com.msa.instagram.clone.social.comment.aggregate.CommentAggregate;
import com.msa.instagram.clone.social.comment.event.CommentUpdateEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;

import java.util.function.Consumer;
import java.util.function.Function;

@Getter
@RequiredArgsConstructor
public enum CommentAggregateField implements AggregateField<CommentAggregate, CommentUpdateEvent> {

    CONTENT(commentUpdateEvent -> commentUpdateEvent.getContent(), pair -> pair.getFirst().setContent(String.valueOf(pair.getSecond())))
    ;

    private final Function<CommentUpdateEvent, Object> getExpress;
    private final Consumer<Pair<CommentAggregate, Object>> setExpress;

    @Override
    public void updateAggregate(CommentAggregate aggregate, CommentUpdateEvent event) {
        setExpress.accept(Pair.of(aggregate, getExpress.apply(event)));
    }
}
