package com.msa.instagram.clone.social.like.service;

import com.msa.instagram.clone.social.like.command.CommentLikeCommand;
import com.msa.instagram.clone.social.like.command.PostLikeCommand;
import com.msa.instagram.clone.social.like.command.UnLikeCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class LikeService {

    private final CommandGateway commandGateway;

    public void postLike(PostLikeCommand command) {
        commandGateway.sendAndWait(command);
    }

    public void commentLike(CommentLikeCommand command) {
        commandGateway.sendAndWait(command);
    }

    public void unLike(UnLikeCommand command) {
        commandGateway.sendAndWait(command);
    }
}
