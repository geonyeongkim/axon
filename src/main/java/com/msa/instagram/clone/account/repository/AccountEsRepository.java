package com.msa.instagram.clone.account.repository;

import com.msa.instagram.clone.account.model.document.AccountEsDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by geonyeong.kim on 2019-12-31
 */
public interface AccountEsRepository extends ElasticsearchRepository<AccountEsDocument, String> {

}
