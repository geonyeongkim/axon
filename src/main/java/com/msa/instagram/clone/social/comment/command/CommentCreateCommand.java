package com.msa.instagram.clone.social.comment.command;

import com.msa.instagram.clone.social.comment.model.vo.CommentCreateRequest;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

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
