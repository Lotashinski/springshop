package com.github.lotashinski.ui.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.lotashinski.ui.client.CategoriesClient;
import com.github.lotashinski.ui.client.OrderClient;
import com.github.lotashinski.ui.client.ProductClient;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

@Configuration
public class FeignConfig {

	private static String HOST = DotenvUtils.dotenv().get("API_HOST", "http://127.0.0.1:8080");

	@Bean
	public CategoriesClient categoryClient() {
		return feignBuilder().target(CategoriesClient.class, HOST);
	}

	@Bean
	public ProductClient productClient() {
		return feignBuilder().target(ProductClient.class, HOST);
	}
	
	@Bean
	public OrderClient orderClient() {
		return feignBuilder().target(OrderClient.class, HOST);
	}
	
	private Feign.Builder feignBuilder() {
		ObjectMapper objectMapper = feignObjectMapper();
		
		return Feign.builder()
				.encoder(new JacksonEncoder(objectMapper)) 
				.decoder(new JacksonDecoder(objectMapper));
	}
	
	private ObjectMapper feignObjectMapper() {
        return new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
	
}
