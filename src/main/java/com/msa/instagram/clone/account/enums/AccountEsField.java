package com.msa.instagram.clone.account.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountEsField {

    USER_NAME(AccountAggregateField.USER_NAME, "userName"),
    PASSWORD(AccountAggregateField.PASSWORD, "password"),
    NICK_NAME(AccountAggregateField.NICK_NAME, "nickname"),
    WEB_SITE(AccountAggregateField.WEB_SITE, "website"),
    INTRO(AccountAggregateField.INTRO, "intro"),
    EMAIL(AccountAggregateField.EMAIL, "email"),
    TELEPHONE(AccountAggregateField.TELEPHONE, "telephone"),
    GENDER(AccountAggregateField.GENDER, "gender"),
    PROFILE_URL(AccountAggregateField.PROFILE_URL, "profileUrl")
    ;
    private static final String isActiveFieldName = "isActive";
    private final AccountAggregateField accountAggregateField;
    private final String esFieldName;

    public static AccountEsField getAccountEsFieldByAccountAggregateField(AccountAggregateField aggregateField) {
        for(AccountEsField accountEsField : values()) {
            if(accountEsField.getAccountAggregateField() == aggregateField) {
                return accountEsField;
            }
        }
        throw new RuntimeException("NOT MATCHED FIELD");
    }

    public static String getIsActiveFieldName() {
        return isActiveFieldName;
    }
}
