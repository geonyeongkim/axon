package com.msa.instagram.clone.account.repository;

import com.msa.instagram.clone.account.model.document.AccountMongoDocument;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


public interface AccountMongoRepository extends MongoRepository<AccountMongoDocument, String> {

    @Query(
            value = "{'event.userName': ?0}",
            sort = "{'timestamp' : -1}"
    )
    List<AccountMongoDocument> findByUserNamesOrderByTimestampDesc(final String userName);

    @Query(
            value = "{'event.userName': ?0}",
            sort = "{'timestamp' : 1}"
    )
    List<AccountMongoDocument> findByUserNamesOrderByTimestampAsc(final String userName);

}
