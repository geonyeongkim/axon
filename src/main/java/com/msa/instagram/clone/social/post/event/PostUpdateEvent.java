package com.msa.instagram.clone.social.post.event;

import com.msa.instagram.clone.common.support.AggregateField;
import com.msa.instagram.clone.social.post.enums.PostAggregateField;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class PostUpdateEvent {

    private String id;
    private String content;
    private long updateTimestamp;
    private List<? extends AggregateField> postAggregateFieldList;

    @Builder
    public PostUpdateEvent(String id, String content, long updateTimestamp, List<PostAggregateField> postAggregateFieldList) {
        this.id = id;
        this.content = content;
        this.updateTimestamp = updateTimestamp;
        this.postAggregateFieldList = postAggregateFieldList;
    }
}
