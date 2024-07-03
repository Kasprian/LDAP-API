package com.example.ldapapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
@EntityScan(basePackages = "com.example.ldapapi.model")
@EnableJpaRepositories(basePackages = "com.example.ldapapi.repository")
public class LdapApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdapApiApplication.class, args);
	}

}
