package com.msa.instagram.clone.social.post.controller;

import com.msa.instagram.clone.social.post.command.PostCreateCommand;
import com.msa.instagram.clone.social.post.command.PostDeleteCommand;
import com.msa.instagram.clone.social.post.command.PostUpdateCommand;
import com.msa.instagram.clone.social.post.model.vo.PostCreateRequest;
import com.msa.instagram.clone.social.post.model.vo.PostUpdateRequest;
import com.msa.instagram.clone.social.post.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private final PostService postService;

    @PostMapping
    public void create(@RequestBody PostCreateRequest postCreateRequest) {
        final PostCreateCommand command = new PostCreateCommand(postCreateRequest);
        postService.create(command);
    }

    @PutMapping
    public void update(@RequestBody PostUpdateRequest postUpdateRequest) {
        final PostUpdateCommand command = new PostUpdateCommand(postUpdateRequest);
        postService.update(command);

    }

    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable String id) {
        postService.delete(new PostDeleteCommand(id));
    }
}
