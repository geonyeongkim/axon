package com.msa.instagram.clone.social.post.command;

import com.msa.instagram.clone.social.post.model.vo.Media;
import com.msa.instagram.clone.social.post.model.vo.PostUpdateRequest;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.List;

@Getter
@ToString
public class PostUpdateCommand {

    @TargetAggregateIdentifier
    private String id;

    private String authorId;
    private String content;
    private long updateTimestamp;

    public PostUpdateCommand(PostUpdateRequest request) {
        this.id = request.getId();
        this.authorId = request.getAuthorId();
        this.content = request.getContent();
        this.updateTimestamp = System.currentTimeMillis();
    }
}
