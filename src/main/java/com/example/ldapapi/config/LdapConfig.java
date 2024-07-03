package com.example.ldapapi.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.ldap.repository.config.EnableLdapRepositories;
import org.springframework.ldap.core.support.BaseLdapPathContextSource;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.ldap.EmbeddedLdapServerContextSourceFactoryBean;
import org.springframework.security.config.ldap.LdapBindAuthenticationManagerFactory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.authentication.LdapAuthenticator;
import org.springframework.security.ldap.search.FilterBasedLdapUserSearch;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.ldap.userdetails.LdapUserDetailsService;
import org.springframework.security.ldap.userdetails.PersonContextMapper;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;
@Configuration
@EnableLdapRepositories(basePackages = "com.example.ldapapi.ldaprepository")
public class LdapConfig {

    @Bean
    @Primary
    public BaseLdapPathContextSource securityContextSource() {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl("ldap://yourldapserver.com:389");
        contextSource.setUserDn("cn=admin,dc=example,dc=com");
        contextSource.setPassword("yourPassword");
        return contextSource;
    }

    @Bean
    public LdapAuthenticator ldapAuthenticator(BaseLdapPathContextSource contextSource) {
        BindAuthenticator authenticator = new BindAuthenticator(contextSource);
        authenticator.setUserSearch(new FilterBasedLdapUserSearch("ou=people", "(uid={0})", contextSource));
        return authenticator;
    }

    @Bean
    public LdapAuthenticationProvider ldapAuthenticationProvider(LdapAuthenticator ldapAuthenticator, BaseLdapPathContextSource contextSource) {
        DefaultLdapAuthoritiesPopulator authoritiesPopulator = new DefaultLdapAuthoritiesPopulator(contextSource, "ou=groups");
        authoritiesPopulator.setGroupRoleAttribute("cn");

        LdapAuthenticationProvider provider = new LdapAuthenticationProvider(ldapAuthenticator, authoritiesPopulator);
        provider.setUserDetailsContextMapper(userDetailsContextMapper());
        return provider;
    }

    @Bean
    public UserDetailsContextMapper userDetailsContextMapper() {
        return new UserDetailsContextMapperImpl();
    }
}