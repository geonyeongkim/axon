package com.msa.instagram.clone.account.model.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.msa.instagram.clone.account.enums.Gender;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountUpdateRequest {

    private String id;
    private String userName;
    private String password;
    private String nickname;
    private boolean isActive;
    private String website;
    private String intro;
    private String email;
    private String telephone;
    private Gender gender;
    private String profileUrl;
}
