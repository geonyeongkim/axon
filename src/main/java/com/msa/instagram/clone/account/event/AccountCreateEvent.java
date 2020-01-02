package com.msa.instagram.clone.account.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class AccountCreateEvent {

    @Setter
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
    
    public AccountCreateEvent(AccountCreateCommand command) {
        this.id = command.getId();
        this.userName = command.getUserName();
        this.password = command.getPassword();
        this.nickname = command.getNickname();
        this.isActive = true;
        this.website = command.getWebsite();
        this.intro = command.getIntro();
        this.email = command.getEmail();
        this.telephone = command.getTelephone();
        this.gender = command.getGender();
        this.profileUrl = command.getProfileUrl();
        this.timestamp = command.getTimestamp();
    }
}
