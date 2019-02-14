package com.vfreiman.spring.springexamples.security.example;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityImpl implements GrantedAuthority {

    private String role;

    public GrantedAuthorityImpl(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
