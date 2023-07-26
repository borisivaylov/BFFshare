package com.example.bffshare.domain.config;

import com.example.storageservice.restexport.ZooStorageRestExport;
import com.example.zoostoreproject.restexport.ZooStoreRestExport;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ZooStorageClientFactory {
    @Bean
    public ZooStorageRestExport getStorageRestExportClient(){
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return Feign.builder()
                .encoder(new JacksonEncoder(objectMapper))
                .decoder(new JacksonDecoder(objectMapper))
                .target(ZooStorageRestExport.class, "http://localhost:8082");

    }
}
