package com.msa.instagram.clone.social.like.command;

import com.msa.instagram.clone.social.like.model.vo.PostLikeRequest;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

import java.util.UUID;

@Getter
@ToString
public class PostLikeCommand {

    @TargetAggregateIdentifier
    private String id;
    private String authorId;
    private String postId;

    public PostLikeCommand(PostLikeRequest request) {
        this.id = UUID.randomUUID().toString();
        this.authorId = request.getAuthorId();
        this.postId = request.getPostId();
    }
}
