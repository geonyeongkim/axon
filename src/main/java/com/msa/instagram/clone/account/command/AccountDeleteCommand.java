package com.msa.instagram.clone.account.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@RequiredArgsConstructor
@ToString
public class AccountDeleteCommand {
    @TargetAggregateIdentifier
    private final String id;
}
