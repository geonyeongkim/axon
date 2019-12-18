package com.msa.instagram.clone.account.service;

import com.msa.instagram.clone.account.command.SignUpCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

/**
 * Created by geonyeong.kim on 2019-12-18
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class AccountService {

    private final CommandGateway commandGateway;

    public void singUp(SignUpCommand signUpCommand) {
        long start = System.currentTimeMillis();
        Object result = commandGateway.sendAndWait(signUpCommand);
        log.info("result => {}", result);
        // TODO: logging
        long end = System.currentTimeMillis();
        log.info("event process duration => {}", end - start);
    }
}
