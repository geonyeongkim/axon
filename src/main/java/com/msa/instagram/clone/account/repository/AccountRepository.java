package com.msa.instagram.clone.account.repository;

import com.msa.instagram.clone.account.model.document.AccountDocument;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface AccountRepository extends MongoRepository<AccountDocument, String> {

    @Query(
            value = "{'event.userName': ?0}",
            sort = "{'timestamp' : -1}"
    )
    List<AccountDocument> findByUserNamesOrderOrderByTimestampDesc(final String userName);

    @Query(
            value = "{'event.userName': ?0}",
            sort = "{'timestamp' : 1}"
    )
    List<AccountDocument> findByUserNamesOrderOrderByTimestampAsc(final String userName);

}
