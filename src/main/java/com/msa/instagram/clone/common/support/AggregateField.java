package com.msa.instagram.clone.common.support;

import org.springframework.data.util.Pair;

public interface AggregateField<A, E> {
    void updateAggregate(A aggregate, E event);
}
