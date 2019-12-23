package com.msa.instagram.clone.account.command;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.msa.instagram.clone.account.enums.Gender;
import com.msa.instagram.clone.account.model.vo.AccountUpdateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * Created by geonyeong.kim on 2019-12-23
 */
@Getter
@ToString
@NoArgsConstructor
public class AccountUpdateCommand {

    @TargetAggregateIdentifier
    private String id;

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

    public AccountUpdateCommand(AccountUpdateRequest request) {
        this.id = request.getId();
        this.userName = request.getUserName();
        this.password = request.getPassword();
        this.ninkname = request.getNinkname();
        this.isActive = request.isActive();
        this.website = request.getWebsite();
        this.intro = request.getIntro();
        this.email = request.getEmail();
        this.telephone = request.getTelephone();
        this.gender = request.getGender();
        this.profileUrl = request.getProfileUrl();
        this.timestamp = System.currentTimeMillis();
    }
}