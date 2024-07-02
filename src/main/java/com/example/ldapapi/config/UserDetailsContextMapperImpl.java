package com.example.ldapapi.config;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.UserDetailsContextMapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;

import java.util.Collection;

public class UserDetailsContextMapperImpl implements UserDetailsContextMapper {

    @Override
    public UserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {
        String password = ctx.getStringAttribute("userPassword");
        return new org.springframework.security.core.userdetails.User(username, password, AuthorityUtils.NO_AUTHORITIES);
    }

    @Override
    public void mapUserToContext(UserDetails user, DirContextAdapter ctx) {
        // Optionally implement this method if you need to map UserDetails back to DirContextOperations
    }
}