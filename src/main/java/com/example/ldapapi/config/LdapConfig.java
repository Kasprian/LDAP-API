package com.example.ldapapi.config;

import com.example.ldapapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;

@Configuration
@EnableLdapRepositories(basePackages = "com.example.ldapapi.repository", repositoryBaseClass = UserRepository.class)
public class LdapConfig {
    @Bean
    @Qualifier("ldapContextSource")
    public BaseLdapPathContextSource ldapContextSource() {
        // LDAP context source configuration
        return new LdapContextSource();
    }
}