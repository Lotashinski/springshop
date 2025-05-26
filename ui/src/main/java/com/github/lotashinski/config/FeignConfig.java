package com.github.lotashinski.config;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.lotashinski.client.CategoriesClient;

@Configuration
public class FeignConfig {

	@Bean
    public CategoriesClient categoryClient() {
        return Feign.builder()
                .decoder(new JacksonDecoder()) 
                .target(CategoriesClient.class, "http://127.0.0.1:8080");
    }
	
}
