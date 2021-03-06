//package com.msa.instagram.clone.account.listener;
//
//import com.msa.instagram.clone.account.enums.AccountEsField;
//import com.msa.instagram.clone.account.event.AccountCreateEvent;
//import com.msa.instagram.clone.account.event.AccountDeleteEvent;
//import com.msa.instagram.clone.account.event.AccountUpdateEvent;
//import com.msa.instagram.clone.account.model.document.AccountEsDocument;
//import com.msa.instagram.clone.account.repository.AccountEsRepository;
//import com.msa.instagram.clone.common.EventListener;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.axonframework.eventhandling.EventHandler;
//import org.elasticsearch.action.index.IndexRequest;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.UpdateQuery;
//import org.springframework.data.elasticsearch.core.query.UpdateQueryBuilder;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.stream.Collectors;
//
//@Slf4j
//@RequiredArgsConstructor
//@Component
//public class AccountEventListener extends EventListener {
//
//    private final AccountEsRepository accountEsRepository;
//    private final ElasticsearchTemplate elasticsearchTemplate;
//
//    @EventHandler
//    public void handle(AccountCreateEvent event) {
//        final AccountEsDocument accountEsDocument = new AccountEsDocument(event);
//        accountEsRepository.save(accountEsDocument);
//    }
//
//    @EventHandler
//    public void handle(AccountUpdateEvent event) {
//        final Map updateMap = event.getAccountAggregateFields()
//                .stream()
//                .collect(
//                        Collectors.toMap(
//                                item -> AccountEsField.getAccountEsFieldByAccountAggregateField(item).getEsFieldName(),
//                                item -> item.getGetExpress().apply(event)
//                        )
//                );
//
//        final UpdateQuery updateQuery = makeUpdateQuery(event.getId(), updateMap, AccountEsDocument.class);
//        elasticsearchTemplate.update(updateQuery);
//    }
//
//    @EventHandler
//    public void handle(AccountDeleteEvent event) {
//        elasticsearchTemplate.update(makeUpdateQuery(event.getId(), new HashMap() {{
//            put(AccountEsField.getIsActiveFieldName(), false);
//        }}, AccountEsDocument.class));
//    }
//}