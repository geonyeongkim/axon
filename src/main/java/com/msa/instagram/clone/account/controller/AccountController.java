package com.msa.instagram.clone.account.controller;

import com.msa.instagram.clone.account.command.SignUpCommand;
import com.msa.instagram.clone.account.model.vo.SignUpRequest;
import com.msa.instagram.clone.account.service.AccountService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/api/account")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public void signUp(@RequestBody SignUpRequest signUpRequest){
        SignUpCommand signUpCommand = SignUpCommand.builder()
                .id(UUID.randomUUID().toString())
                .signUpRequest(signUpRequest)
                .timestamp(System.currentTimeMillis())
                .build();
        // command 로깅.
        accountService.singUp(signUpCommand);
    }
}
