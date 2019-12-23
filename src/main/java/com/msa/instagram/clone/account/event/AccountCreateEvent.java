package com.msa.instagram.clone.account.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.enums.Gender;
import com.msa.instagram.clone.common.enums.EventType;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class AccountCreateEvent {
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
    
    public AccountCreateEvent(AccountCreateCommand command) {
        this.userName = command.getUserName();
        this.password = command.getPassword();
        this.ninkname = command.getNinkname();
        this.isActive = command.isActive();
        this.website = command.getWebsite();
        this.intro = command.getIntro();
        this.email = command.getEmail();
        this.telephone = command.getTelephone();
        this.gender = command.getGender();
        this.profileUrl = command.getProfileUrl();
        this.timestamp = command.getTimestamp();
    }
}
