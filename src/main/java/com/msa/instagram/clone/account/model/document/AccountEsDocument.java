package com.msa.instagram.clone.account.model.document;

import com.msa.instagram.clone.account.enums.Gender;
import com.msa.instagram.clone.account.event.AccountCreateEvent;
import com.msa.instagram.clone.account.event.AccountUpdateEvent;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by geonyeong.kim on 2019-12-31
 */
@Data
@Document(indexName = "account", type = "_doc")
public class AccountEsDocument {

    @Id
    private String id;
    private String userName;
    private String password;
    private String nickname;
    @Field(type = FieldType.Boolean)
    private Boolean isActive;
    private String website;
    private String intro;
    private String email;
    private String telephone;
    private Gender gender;
    private String profileUrl;
    private List<String> followerIdList = new ArrayList<>();
    private List<String> followingIdList = new ArrayList<>();;

    public AccountEsDocument(AccountCreateEvent event) {
        this.id = event.getId();
        this.userName = event.getUserName();
        this.password = event.getPassword();
        this.nickname = event.getNickname();
        this.isActive = event.isActive();
        this.website = event.getWebsite();
        this.intro = event.getIntro();
        this.email = event.getTelephone();
        this.gender = event.getGender();
        this.profileUrl = event.getProfileUrl();
    }
}
