package com.msa.instagram.clone;

import com.msa.instagram.clone.account.event.AccountUpdateEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;


@Slf4j
public class CloneApplicationTests {

    @Test
    public void test() {
        AccountUpdateEvent accountUpdateEvent = AccountUpdateEvent.builder().build();
        log.info("accountUpdateEvent => {}", accountUpdateEvent);
        log.info("value => {}", accountUpdateEvent.getEmail());
        log.info("random string => {}", RandomStringUtils.randomAlphabetic(20));


    }
}
