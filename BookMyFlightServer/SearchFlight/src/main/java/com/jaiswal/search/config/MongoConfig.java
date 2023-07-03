package com.jaiswal.search.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
public class MongoConfig extends AbstractMongoClientConfiguration {

    @Override
    protected String getDatabaseName() {
        return "searchdb";
    }
    
    @Bean
    public MongoTemplate mongoTemplate() {
    	MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        SimpleMongoClientDatabaseFactory databaseFactory = new SimpleMongoClientDatabaseFactory(mongoClient, "searchdb");
        return new MongoTemplate(databaseFactory);
    }
    
    @Override
    public MongoCustomConversions customConversions() {
        return new MongoCustomConversions(Arrays.asList(
                new CustomDateConverters.DateToStringConverter(),
                new CustomDateConverters.StringToDateConverter()
        ));
    }

}