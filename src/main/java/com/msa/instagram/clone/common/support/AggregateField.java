package com.msa.instagram.clone.common.support;

import com.msa.instagram.clone.common.event.UpdateEvent;

public interface AggregateField<A, E extends UpdateEvent> {
    void updateAggregate(A aggregate, E event);
}
