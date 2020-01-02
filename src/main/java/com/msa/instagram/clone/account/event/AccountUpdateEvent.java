package com.msa.instagram.clone.account.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.instagram.clone.account.enums.AccountAggregateField;
import com.msa.instagram.clone.account.enums.Gender;
import com.msa.instagram.clone.account.event.AccountEvent;
import lombok.*;

import java.util.List;

/**
 * Created by geonyeong.kim on 2019-12-23
 */
@Getter
@NoArgsConstructor
@ToString
public class AccountUpdateEvent implements AccountEvent {

    private String id;
    private String userName;
    private String password;
    private String nickname;
    @JsonProperty("isActive")
    private boolean isActive;
    private String website;
    private String intro;
    private String email;
    private String telephone;
    private Gender gender;
    private String profileUrl;
    private long timestamp;
    private List<AccountAggregateField> accountAggregateFields;

    @Builder
    public AccountUpdateEvent(String id, String userName, String password, String nickname, boolean isActive, String website, String intro, String email, String telephone, Gender gender, String profileUrl, long timestamp, List<AccountAggregateField> accountAggregateFields) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.nickname = nickname;
        this.isActive = isActive;
        this.website = website;
        this.intro = intro;
        this.email = email;
        this.telephone = telephone;
        this.gender = gender;
        this.profileUrl = profileUrl;
        this.timestamp = timestamp;
        this.accountAggregateFields = accountAggregateFields;
    }
}
