package com.example.ldapapi.ldaprepository;

import com.example.ldapapi.model.User;
import org.springframework.data.ldap.repository.LdapRepository;
import org.springframework.data.ldap.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LdapUserRepository extends LdapRepository<User> {
    @Query("(uid={username})")
    User findByUsername(@Param("username") String username);
}