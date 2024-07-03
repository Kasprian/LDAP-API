package com.example.ldapapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.example.ldapapi.repository")
public class JpaConfig {
    // JPA specific configuration (if needed)
}