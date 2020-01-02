package com.msa.instagram.clone.account.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum AccountEsField {

    USER_NAME(AccountAggregateField.USER_NAME, "userName"),
    PASSWORD(AccountAggregateField.PASSWORD, "password"),
    NICK_NAME(AccountAggregateField.NICK_NAME, "nickname"),
    IS_ACTIVE(AccountAggregateField.IS_ACTIVE, "isActive"),
    WEB_SITE(AccountAggregateField.WEB_SITE, "website"),
    INTRO(AccountAggregateField.INTRO, "intro"),
    EMAIL(AccountAggregateField.EMAIL, "email"),
    TELEPHONE(AccountAggregateField.TELEPHONE, "telephone"),
    GENDER(AccountAggregateField.GENDER, "gender"),
    PROFILE_URL(AccountAggregateField.PROFILE_URL, "profileUrl")
    ;
    private AccountAggregateField accountAggregateField;
    private String esFieldName;

    public static AccountEsField getAccountEsFieldByAccountAggregateField(AccountAggregateField aggregateField) {
        for(AccountEsField accountEsField : values()) {
            if(accountEsField.getAccountAggregateField() == aggregateField) {
                return accountEsField;
            }
        }
        throw new RuntimeException("NOT MATCHED FIELD");
    }
}
