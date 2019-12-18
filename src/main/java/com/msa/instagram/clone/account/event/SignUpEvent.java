package com.msa.instagram.clone.account.event;

import com.msa.instagram.clone.account.model.vo.SignUpRequest;
import lombok.Builder;
import lombok.Data;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Data
public class SignUpEvent {

    private SignUpRequest signUpRequest;
    private long timestamp;

    @Builder
    public SignUpEvent(SignUpRequest signUpRequest, long timestamp) {
        this.signUpRequest = signUpRequest;
        this.timestamp = timestamp;
    }
}
