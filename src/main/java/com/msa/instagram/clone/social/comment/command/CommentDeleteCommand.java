package com.msa.instagram.clone.social.comment.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Slf4j
@Getter
@RequiredArgsConstructor
public class CommentDeleteCommand {

    @TargetAggregateIdentifier
    private final String id;
}
