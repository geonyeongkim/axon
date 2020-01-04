package com.msa.instagram.clone.social.post.event;

import com.msa.instagram.clone.social.post.command.PostCreateCommand;
import com.msa.instagram.clone.social.post.model.vo.Media;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
public class PostCreateEvent {

    @Setter
    private String id;
    private String authorId;
    private String content;
    private List<Media> mediaList;
    private boolean isActive;
    private long createTimestamp;
    private long updateTimestamp;

    public PostCreateEvent(PostCreateCommand command) {
        this.id = command.getId();
        this.authorId = command.getAuthorId();
        this.content = command.getContent();
        this.mediaList = command.getMediaList();
        this.isActive = true;
        this.createTimestamp = command.getCreateTimestamp();
        this.updateTimestamp = command.getUpdateTimestamp();
    }
}
