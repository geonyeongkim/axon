package com.msa.instagram.clone.social.comment.event;

import com.msa.instagram.clone.common.event.UpdateEvent;
import com.msa.instagram.clone.common.support.AggregateField;
import com.msa.instagram.clone.social.comment.enums.CommentAggregateField;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class CommentUpdateEvent extends CommentEvent implements UpdateEvent {

    private String content;
    private List<CommentAggregateField> commentAggregateFields;

    @Builder
    public CommentUpdateEvent(String id, String postId, String content, List<CommentAggregateField> commentAggregateFields) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.commentAggregateFields = commentAggregateFields;
    }
}
