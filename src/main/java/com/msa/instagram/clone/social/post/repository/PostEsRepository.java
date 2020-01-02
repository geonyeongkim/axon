package com.msa.instagram.clone.social.post.repository;

import com.msa.instagram.clone.social.post.model.document.PostEsDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostEsRepository extends ElasticsearchRepository<PostEsDocument, String> {
}
