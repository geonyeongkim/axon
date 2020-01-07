package com.msa.instagram.clone.social.post.command;

import com.msa.instagram.clone.social.post.model.vo.Media;
import com.msa.instagram.clone.social.post.model.vo.PostCreateRequest;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
public class PostCreateCommand {

    @TargetAggregateIdentifier
    private String id;

    private String authorId;
    private String content;
    private List<Media> mediaList;
    private long createTimestamp;
    private long updateTimestamp;

    public PostCreateCommand(PostCreateRequest request) {
        this.id = UUID.randomUUID().toString();
        this.authorId = request.getAuthorId();
        this.content = request.getContent();
        this.mediaList = request.getMediaList();
        final long t = System.currentTimeMillis();
        this.createTimestamp = t;
        this.updateTimestamp = t;
    }
}
