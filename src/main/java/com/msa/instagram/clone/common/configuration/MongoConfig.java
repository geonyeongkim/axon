package com.msa.instagram.clone.common.configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig  extends  AbstractMongoClientConfiguration {

    @Value("${spring.data.mongodb.address}")
    private String mongodbAddress;

    @Value("${spring.data.mongodb.database}")
    private String mongodbDatabase;

    @Override
    public MongoClient mongoClient() {
        return MongoClients.create(mongodbAddress);
    }

    @Override
    protected String getDatabaseName() {
        return mongodbDatabase;
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), mongodbDatabase);
    }
}
