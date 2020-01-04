package com.msa.instagram.clone.social.like.listener;

import com.msa.instagram.clone.social.like.enums.LikeType;
import com.msa.instagram.clone.social.like.event.CommentLikeEvent;
import com.msa.instagram.clone.social.like.event.PostLikeEvent;
import com.msa.instagram.clone.social.like.event.UnLikeEvent;
import com.msa.instagram.clone.social.post.model.document.LikeEs;
import com.msa.instagram.clone.social.post.model.document.PostEsDocument;
import com.msa.instagram.clone.social.post.repository.PostEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.axonframework.eventhandling.EventHandler;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class LikeEventListener {

    private final PostEsRepository postEsRepository;
    private final ElasticsearchTemplate elasticsearchTemplate;

    @EventHandler
    public void on(PostLikeEvent event) {
        PostEsDocument postEsDocument = findPostEsDocumentByPostId(event.getPostId());
        postEsDocument.getLikes().add(new LikeEs(event));
        postEsRepository.save(postEsDocument);
    }

    @EventHandler
    public void on(CommentLikeEvent event) {
        log.info("CommentLikeEvent => {}", event);
        final PostEsDocument postEsDocument = findPostEsDocumentByCommentId(event.getCommentId());
        postEsDocument.getComments().
                forEach(item -> {
                    if(StringUtils.equals(item.getId(), event.getCommentId())) {
                        item.getLikes().add(new LikeEs(event));
                        return;
                    }
                });
        postEsRepository.save(postEsDocument);
    }

    @EventHandler
    public void on(UnLikeEvent event) {
        final PostEsDocument postEsDocument =
                (event.getLikeType() == LikeType.POST) ?
                        findPostEsDocumentByPostId(event.getTargetId()) : findPostEsDocumentByCommentId(event.getTargetId());
        if(event.getLikeType() == LikeType.POST) {
            postEsDocument.setLikes(filteredLikeEsList(postEsDocument.getLikes(), event.getId()));
        } else {
            postEsDocument.getComments().
                    forEach(commentEs -> {
                        if(StringUtils.equals(commentEs.getId(), event.getTargetId())) {
                            commentEs.setLikes(filteredLikeEsList(commentEs.getLikes(), event.getId()));
                            return;
                        }
                    });
        }
        postEsRepository.save(postEsDocument);
    }

    private List<LikeEs> filteredLikeEsList(final List<LikeEs> likeEsList, String id) {
        return likeEsList
                .stream()
                .filter(item -> !StringUtils.equals(item.getId(), id))
                .collect(Collectors.toList());
    }

    private PostEsDocument findPostEsDocumentByPostId(String postId) {
        return postEsRepository.findById(postId).orElseThrow();
    }

    private PostEsDocument findPostEsDocumentByCommentId(String commentId) {
        QueryBuilder builder = nestedQuery("comments", boolQuery().must(matchQuery("comments.id", commentId)), ScoreMode.None);
        SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).build();
        List<PostEsDocument> postEsDocumentList = elasticsearchTemplate.queryForList(searchQuery, PostEsDocument.class);
        log.info("postEsDocumentList => {}", postEsDocumentList);

        if (postEsDocumentList.size() > 1) {
            throw new RuntimeException();
        }
        return postEsDocumentList.get(0);
    }
}
