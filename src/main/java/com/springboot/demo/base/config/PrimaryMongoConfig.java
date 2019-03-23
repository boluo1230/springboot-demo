package com.springboot.demo.base.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb")
public class PrimaryMongoConfig extends AbstractMongoConfig {

    @Primary
    @Bean(name = "localMongoTemplate")
    @Override
    public MongoTemplate getMongoTemplate() {
        return new MongoTemplate(mongoDbFactory());
    }
}