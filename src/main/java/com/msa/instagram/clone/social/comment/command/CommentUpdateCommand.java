package com.msa.instagram.clone.social.comment.command;

import com.msa.instagram.clone.common.command.UpdateCommand;
import com.msa.instagram.clone.social.comment.model.vo.CommentUpdateRequest;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.commandhandling.TargetAggregateIdentifier;

@Getter
@ToString
public class CommentUpdateCommand implements UpdateCommand {

    @TargetAggregateIdentifier
    private String id;

    private String content;

    public CommentUpdateCommand(CommentUpdateRequest request) {
        this.id = request.getId();
        this.content = request.getContent();
    }
}
