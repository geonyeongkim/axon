package com.msa.instagram.clone.account.model.vo;

import lombok.Data;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Data
public class SignUpRequest {
    private String username;
    private String password;
    private String nickname;
}
