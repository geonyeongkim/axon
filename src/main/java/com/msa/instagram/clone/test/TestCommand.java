package com.msa.instagram.clone.test;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by geonyeong.kim on 2020-01-07
 */
@Slf4j
@Getter
@RequiredArgsConstructor
public class TestCommand {
    @TargetAggregateIdentifier
    private final String id;
}
