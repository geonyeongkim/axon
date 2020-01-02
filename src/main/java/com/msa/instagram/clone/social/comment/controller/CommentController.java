package com.msa.instagram.clone.social.comment.controller;

import com.msa.instagram.clone.social.comment.command.CommentCreateCommand;
import com.msa.instagram.clone.social.comment.command.CommentDeleteCommand;
import com.msa.instagram.clone.social.comment.command.CommentUpdateCommand;
import com.msa.instagram.clone.social.comment.model.vo.CommentCreateRequest;
import com.msa.instagram.clone.social.comment.model.vo.CommentUpdateRequest;
import com.msa.instagram.clone.social.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping(value = "create")
    public void create(@RequestBody CommentCreateRequest commentCreateRequest) {
        final CommentCreateCommand command = new CommentCreateCommand(commentCreateRequest);
        commentService.create(command);
    }

    @PostMapping(value = "update")
    public void update(@RequestBody CommentUpdateRequest commentUpdateRequest) {
        final CommentUpdateCommand command = new CommentUpdateCommand(commentUpdateRequest);
        commentService.update(command);
    }

    @GetMapping(value = "delete/{id}")
    public void delete(@PathVariable String id) {
        commentService.delete(new CommentDeleteCommand(id));
    }
}
