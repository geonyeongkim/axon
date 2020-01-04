package com.msa.instagram.clone.social.post.event;

import com.msa.instagram.clone.common.event.UpdateEvent;
import com.msa.instagram.clone.common.support.AggregateField;
import com.msa.instagram.clone.social.post.enums.PostAggregateField;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PostUpdateEvent implements UpdateEvent {

    private String id;
    private String content;
    private long updateTimestamp;
    private List<PostAggregateField> postAggregateFieldList;

    @Builder
    public PostUpdateEvent(String id, String content, long updateTimestamp, List<PostAggregateField> postAggregateFieldList) {
        this.id = id;
        this.content = content;
        this.updateTimestamp = updateTimestamp;
        this.postAggregateFieldList = postAggregateFieldList;
    }
}
