package com.msa.instagram.clone.social.like.command;

import com.msa.instagram.clone.social.like.model.vo.PostLikeRequest;
import java.util.UUID;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

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
