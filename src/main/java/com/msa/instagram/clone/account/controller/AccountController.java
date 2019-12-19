package com.msa.instagram.clone.account.controller;

import com.msa.instagram.clone.account.command.SignUpCommand;
import com.msa.instagram.clone.account.model.vo.SignUpRequest;
import com.msa.instagram.clone.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "signUp")
    public void signUp(@RequestBody SignUpRequest signUpRequest) {
        log.info("signUo controller enter!");
        SignUpCommand signUpCommand = new SignUpCommand(1, "test");
        log.info("signUpCommand => {}", signUpCommand);
        // command 로깅.
        accountService.singUp(signUpCommand);
    }
}
