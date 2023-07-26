package com.example.bffshare.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.example.bffshare")
@EntityScan(basePackages = "com.example.storageservice.persistence.entity")
//@EnableJpaRepositories(basePackages = "com.example.bff.persistence.repository")
@EnableFeignClients(basePackages ="com.example.bffshare")
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class BfFshareApplication {

    public static void main(String[] args) {
        SpringApplication.run(BfFshareApplication.class, args);
    }

}
