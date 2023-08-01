package com.jaiswal.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "bookdb";
    }
    
    @Bean
    public MongoTemplate mongoTemplate() {
    	MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        SimpleMongoClientDatabaseFactory databaseFactory = new SimpleMongoClientDatabaseFactory(mongoClient, "bookdb");
        return new MongoTemplate(databaseFactory);
    }

}
