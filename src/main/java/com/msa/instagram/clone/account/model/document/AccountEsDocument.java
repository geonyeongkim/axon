package com.msa.instagram.clone.account.model.document;

import com.msa.instagram.clone.account.enums.Gender;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Id;
import lombok.Builder;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by geonyeong.kim on 2019-12-31
 */
@Document(indexName = "account", type = "_doc")
public class AccountEsDocument {

    @Id
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
    private List<String> followerIdList = new ArrayList<>();
    private List<String> followingIdList = new ArrayList<>();;

    @Builder
    public AccountEsDocument(String id, String userName, String password, String nickname, boolean isActive, String website, String intro,
            String email, String telephone, Gender gender, String profileUrl, List<String> followerIdList,
            List<String> followingIdList) {
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
        this.followerIdList = followerIdList;
        this.followingIdList = followingIdList;
    }
}
