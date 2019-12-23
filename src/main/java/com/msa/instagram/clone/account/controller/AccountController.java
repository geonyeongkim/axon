package com.msa.instagram.clone.account.controller;

import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.model.vo.AccountCreateRequest;
import com.msa.instagram.clone.account.model.vo.AccountUpdateRequest;
import com.msa.instagram.clone.account.repository.AccountRepository;
import com.msa.instagram.clone.account.service.AccountService;
import com.msa.instagram.clone.common.utils.JacksonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @PostMapping(value = "create")
    public void create(@RequestBody AccountCreateRequest accountCreateRequest) {
        log.info("accountRepository => {}", accountRepository);
        final AccountCreateCommand command = new AccountCreateCommand(accountCreateRequest);
        accountService.create(command);

    }

    @PostMapping(value = "update")
    public void update(@RequestBody AccountUpdateRequest accountUpdateRequest) {
        log.info("accountUpdateRequest => {}", accountUpdateRequest);
    }

    @PostMapping(value = "delete/{id}")
    public void delete(@PathVariable long id) {
        log.info("id => {}", id);
    }
}