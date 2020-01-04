package com.msa.instagram.clone.common.support;

public interface AggregateField<A, E> {
    void updateAggregate(A aggregate, E event);
}
