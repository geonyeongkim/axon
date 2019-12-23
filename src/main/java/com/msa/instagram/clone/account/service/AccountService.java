package com.msa.instagram.clone.account.service;

import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.command.AccountUpdateCommand;
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


    public void create(AccountCreateCommand command) {
        // 필수 필드 체크
        commandGateway.sendAndWait(command);
        log.info("complete create");
    }

    public void update(AccountUpdateCommand command) {
        log.info("account service update ");
        commandGateway.sendAndWait(command);
        log.info("complete update");
    }
}
