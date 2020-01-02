package com.msa.instagram.clone.social.like.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@RequiredArgsConstructor
@ToString
public class UnLikeCommand {

    @TargetAggregateIdentifier
    private final String id;
}
