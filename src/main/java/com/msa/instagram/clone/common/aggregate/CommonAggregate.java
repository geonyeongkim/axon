package com.msa.instagram.clone.common.aggregate;

import org.axonframework.commandhandling.model.AggregateIdentifier;

import java.util.Optional;

public abstract class CommonAggregate<E, C> {

    @AggregateIdentifier
    protected String id;
    protected abstract Optional<E> diff(C command);
}
