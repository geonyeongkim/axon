package com.msa.instagram.clone.common.aggregate;

import com.msa.instagram.clone.common.command.UpdateCommand;
import com.msa.instagram.clone.common.event.UpdateEvent;
import lombok.Setter;
import org.axonframework.commandhandling.model.AggregateIdentifier;

import java.util.Optional;

public abstract class CommonAggregate<E extends UpdateEvent, C extends UpdateCommand> {

    @AggregateIdentifier
    @Setter
    private String id;
    protected abstract Optional<E> diff(C command);
}
