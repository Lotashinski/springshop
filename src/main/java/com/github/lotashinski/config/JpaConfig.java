package com.github.lotashinski.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.lotashinski.repository")
public class JpaConfig {

}
