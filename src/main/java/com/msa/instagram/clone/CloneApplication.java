package com.msa.instagram.clone;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(
        exclude = {DataSourceAutoConfiguration.class}
)
public class CloneApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(CloneApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
