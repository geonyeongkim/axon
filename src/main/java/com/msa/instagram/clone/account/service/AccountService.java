package com.msa.instagram.clone.account.service;

import com.msa.instagram.clone.account.command.AccountCreateCommand;
import com.msa.instagram.clone.account.command.AccountDeleteCommand;
import com.msa.instagram.clone.account.command.AccountUpdateCommand;
import com.msa.instagram.clone.account.repository.AccountEsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.hibernate.validator.internal.metadata.core.AnnotationProcessingOptionsImpl;
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
        commandGateway.sendAndWait(command);
    }

    public void update(AccountUpdateCommand command) {
        commandGateway.sendAndWait(command);
    }

    public void delete(AccountDeleteCommand command) {
        commandGateway.sendAndWait(command);
    }
}
