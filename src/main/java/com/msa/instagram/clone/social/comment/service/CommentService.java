package com.msa.instagram.clone.social.comment.service;

import com.msa.instagram.clone.social.comment.command.CommentCreateCommand;
import com.msa.instagram.clone.social.comment.command.CommentDeleteCommand;
import com.msa.instagram.clone.social.comment.command.CommentUpdateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommandGateway commandGateway;

    public void create(CommentCreateCommand command) {
        commandGateway.sendAndWait(command);
    }

    public void update(CommentUpdateCommand command) {
        commandGateway.sendAndWait(command);
    }

    public void delete(CommentDeleteCommand command) {
        commandGateway.sendAndWait(command);
    }
}
