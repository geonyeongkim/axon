package com.msa.instagram.clone.account.model.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.instagram.clone.account.enums.Gender;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountCreateRequest {

    private String userName;
    private String password;
    private String nickname;
    private String website;
    private String intro;
    private String email;
    private String telephone;
    private Gender gender;
    private String profileUrl;
}
