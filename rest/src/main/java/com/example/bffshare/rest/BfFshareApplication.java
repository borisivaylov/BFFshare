package com.example.bffshare.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = "com.example.bffshare")
@EntityScan(basePackages = "com.example.bffshare.persistence.entity")
@EnableJpaRepositories(basePackages = "com.example.bffshare.persistence.repository")
@EnableFeignClients(basePackages ="com.example.bffshare")
@SpringBootApplication
public class BfFshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(BfFshareApplication.class, args);
    }

}
