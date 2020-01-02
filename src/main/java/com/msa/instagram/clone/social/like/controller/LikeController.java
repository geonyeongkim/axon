package com.msa.instagram.clone.social.like.controller;

import com.msa.instagram.clone.social.comment.command.CommentCreateCommand;
import com.msa.instagram.clone.social.comment.model.vo.CommentCreateRequest;
import com.msa.instagram.clone.social.like.command.CommentLikeCommand;
import com.msa.instagram.clone.social.like.command.PostLikeCommand;
import com.msa.instagram.clone.social.like.command.UnLikeCommand;
import com.msa.instagram.clone.social.like.model.vo.CommentLikeRequest;
import com.msa.instagram.clone.social.like.model.vo.PostLikeRequest;
import com.msa.instagram.clone.social.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/like")
public class LikeController {

    private final LikeService likeService;

    @PostMapping(value = "/post")
    public void postLike(@RequestBody PostLikeRequest postLikeRequest) {
        final PostLikeCommand command = new PostLikeCommand(postLikeRequest);
        likeService.postLike(command);
    }

    @PostMapping(value = "/comment")
    public void commentLike(@RequestBody CommentLikeRequest commentLikeRequest) {
        final CommentLikeCommand command = new CommentLikeCommand(commentLikeRequest);
        likeService.commentLike(command);
    }

    @DeleteMapping(value = "/{id}")
    public void unLike(@PathVariable String id) {
        likeService.unLike(new UnLikeCommand(id));
    }
}
