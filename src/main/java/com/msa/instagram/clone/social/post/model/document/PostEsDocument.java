package com.msa.instagram.clone.social.post.model.document;

import com.msa.instagram.clone.social.post.model.vo.Media;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.util.List;

@Data
@Document(indexName = "post", type = "_doc")
public class PostEsDocument {

    @Id
    private String id;
    private String authorId;
    private String content;
    private List<Media> mediaList;
    @Field(type = FieldType.Boolean)
    private Boolean isActive;
    private long createTimestamp;
    private long updateTimestamp;
}
