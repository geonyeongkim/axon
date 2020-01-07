package com.msa.instagram.clone.social.post.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@RequiredArgsConstructor
@ToString
public class PostDeleteCommand {
    @TargetAggregateIdentifier
    private final String id;
}
