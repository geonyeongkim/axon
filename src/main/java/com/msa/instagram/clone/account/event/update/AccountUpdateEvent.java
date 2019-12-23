package com.msa.instagram.clone.account.event.update;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.instagram.clone.account.enums.Gender;
import com.msa.instagram.clone.account.event.AccountEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by geonyeong.kim on 2019-12-23
 */
@Getter
@NoArgsConstructor
@ToString
public class AccountUpdateEvent implements AccountEvent {

    private String userName;
    private String password;
    private String ninkname;
    @JsonProperty("isActive")
    private boolean isActive;
    private String website;
    private String intro;
    private String email;
    private String telephone;
    private Gender gender;
    private String profileUrl;
    private long timestamp;

}
