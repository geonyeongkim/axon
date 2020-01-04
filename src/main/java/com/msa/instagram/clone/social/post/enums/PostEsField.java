package com.msa.instagram.clone.social.post.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PostEsField {
    CONTENT(PostAggregateField.CONTENT, "content"),
    UPDATE_TIME(PostAggregateField.UPDATE_TIME, "updateTimestamp")
    ;
    private static final String isActiveFieldName = "isActive";
    private final PostAggregateField postAggregateField;
    private final String esFieldName;

    public static PostEsField getPostEsFieldByPostAggregateField(PostAggregateField aggregateField) {
        for(PostEsField postEsField: values()) {
            if(postEsField.getPostAggregateField() == aggregateField) {
                return postEsField;
            }
        }
        throw new RuntimeException("NOT MATCHED FIELD");
    }

    public static String getIsActiveFieldName() {
        return isActiveFieldName;
    }
}
