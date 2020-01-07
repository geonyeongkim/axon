package com.msa.instagram.clone.test;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by geonyeong.kim on 2020-01-07
 */
@Slf4j
@Component
public class TestListener {

    @EventHandler
    public void on(TestEvent event) {
        log.info("TestListener TestEvent => {}", event);
    }
}
