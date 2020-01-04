package com.msa.instagram.clone.social.post.model.document;

import com.msa.instagram.clone.social.like.aggregate.LikeAggregate;
import com.msa.instagram.clone.social.post.event.PostCreateEvent;
import com.msa.instagram.clone.social.post.model.vo.Media;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@NoArgsConstructor
@Document(indexName = "post", type = "_doc")
public class PostEsDocument {

    @Id
    private String id;
    private String authorId;
    private String content;
    private long createTimestamp;
    private long updateTimestamp;

    @Field(type = FieldType.Nested)
    private List<Media> mediaList;

    @Field(type = FieldType.Nested)
    private List<CommentEs> comments;

    @Field(type = FieldType.Nested)
    private List<LikeEs> likes;

    @Field(type = FieldType.Boolean)
    private Boolean isActive;

    public PostEsDocument(PostCreateEvent event)  {
        this.id = event.getId();
        this.authorId = event.getAuthorId();
        this.content = event.getContent();
        this.createTimestamp = event.getCreateTimestamp();
        this.updateTimestamp = event.getUpdateTimestamp();
        this.mediaList = Objects.nonNull(event.getMediaList())? event.getMediaList() : new ArrayList<>();
        this.isActive = true;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }
}
