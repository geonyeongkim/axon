package com.msa.instagram.clone.social.like.command;

import com.msa.instagram.clone.social.like.model.vo.CommentLikeRequest;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
public class CommentLikeCommand {

    @TargetAggregateIdentifier
    private String id;
    private String authorId;
    private String commentId;

    public CommentLikeCommand(CommentLikeRequest request) {
        this.id = UUID.randomUUID().toString();
        this.authorId = request.getAuthorId();
        this.commentId = request.getCommentId();
    }
}
