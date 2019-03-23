package com.springboot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.springboot.demo.mapper")
@EnableCaching
@EnableTransactionManagement
public class SpringBootDemo extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemo.class, args);
    }


    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringBootDemo.class);
    }

}
