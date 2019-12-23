package com.msa.instagram.clone.account.repository;

import com.msa.instagram.clone.account.model.document.AccountDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<AccountDocument, Long> {
}
