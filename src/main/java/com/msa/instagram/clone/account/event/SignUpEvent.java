package com.msa.instagram.clone.account.event;

import com.msa.instagram.clone.account.model.vo.SignUpRequest;
import lombok.Builder;
import lombok.Data;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Data
public class SignUpEvent {

    private String name;
    private long timestamp;

    public SignUpEvent(String name, long timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }
}
