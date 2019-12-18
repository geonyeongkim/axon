package com.msa.instagram.clone.account.command;

import com.msa.instagram.clone.account.model.vo.SignUpRequest;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Data
public class SignUpCommand {

    @TargetAggregateIdentifier
    private String id;

    private SignUpRequest signUpRequest;

    private long timestamp;

    @Builder
    public SignUpCommand(String id, SignUpRequest signUpRequest, long timestamp) {
        this.id = id;
        this.signUpRequest = signUpRequest;
        this.timestamp = timestamp;
    }
}
