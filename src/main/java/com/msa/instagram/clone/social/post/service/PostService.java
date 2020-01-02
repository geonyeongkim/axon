package com.msa.instagram.clone.social.post.service;

import com.msa.instagram.clone.social.post.command.PostCreateCommand;
import com.msa.instagram.clone.social.post.command.PostDeleteCommand;
import com.msa.instagram.clone.social.post.command.PostUpdateCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class PostService {

    private final CommandGateway commandGateway;

    public void create(PostCreateCommand command) {
        commandGateway.sendAndWait(command);
    }

    public void update(PostUpdateCommand command) {
        commandGateway.sendAndWait(command);
    }

    public void delete(PostDeleteCommand command) {
        commandGateway.sendAndWait(command);
    }
}
