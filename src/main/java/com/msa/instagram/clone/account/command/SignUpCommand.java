package com.msa.instagram.clone.account.command;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Data
@RequiredArgsConstructor
public class SignUpCommand {

    @TargetAggregateIdentifier
    private final Integer accountAggregateId;
    private final String name;

//    private SignUpRequest signUpRequest;

//    private long timestamp;


}
