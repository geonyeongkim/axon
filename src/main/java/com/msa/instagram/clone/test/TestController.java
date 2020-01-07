package com.msa.instagram.clone.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by geonyeong.kim on 2020-01-07
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/test")
public class TestController {

    private final CommandGateway commandGateway;

    @GetMapping(value = "{id}")
    public void test(@PathVariable String id) {
        commandGateway.sendAndWait(new TestCommand(id));
    }
}
