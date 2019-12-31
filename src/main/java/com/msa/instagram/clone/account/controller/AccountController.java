package com.msa.instagram.clone.account.controller;

import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.command.AccountUpdateCommand;
import com.msa.instagram.clone.account.model.document.AccountEsDocument;
import com.msa.instagram.clone.account.model.vo.AccountCreateRequest;
import com.msa.instagram.clone.account.model.vo.AccountUpdateRequest;
import com.msa.instagram.clone.account.service.AccountService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final ElasticsearchRepository elasticsearchRepository;

    @PostMapping(value = "create")
    public void create(@RequestBody AccountCreateRequest accountCreateRequest) {
        final AccountCreateCommand command = new AccountCreateCommand(accountCreateRequest);
        accountService.create(command);

    }

    @PostMapping(value = "update")
    public void update(@RequestBody AccountUpdateRequest accountUpdateRequest) {
        final AccountUpdateCommand command = new AccountUpdateCommand(accountUpdateRequest);
        log.info("command => {}", command);
        accountService.update(command);

    }

    @PostMapping(value = "delete/{id}")
    public void delete(@PathVariable String id) {
        log.info("id => {}", id);
    }

    @GetMapping(value = "test")
    public void test() {
        final String uuid = UUID.randomUUID().toString();
        log.info("uuid => {}", uuid);

        AccountEsDocument accountEsDocument = AccountEsDocument.builder()
                .id(uuid)
                .userName("testGeonyeong")
                .password("testPassword")
                .build();
        elasticsearchRepository.save(accountEsDocument);
    }
}