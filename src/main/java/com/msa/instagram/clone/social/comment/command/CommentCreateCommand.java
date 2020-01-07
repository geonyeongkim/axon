package com.msa.instagram.clone.social.comment.command;

import com.msa.instagram.clone.social.comment.model.vo.CommentCreateRequest;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
public class CommentCreateCommand {

    @TargetAggregateIdentifier
    private String id;

    private String authorId;
    private String postId;
    private String content;
    private long timestamp;

    public CommentCreateCommand(CommentCreateRequest request) {
        this.id = UUID.randomUUID().toString();
        this.authorId = request.getAuthorId();
        this.postId = request.getPostId();
        this.content = request.getContent();
        this.timestamp = System.currentTimeMillis();
    }
}
