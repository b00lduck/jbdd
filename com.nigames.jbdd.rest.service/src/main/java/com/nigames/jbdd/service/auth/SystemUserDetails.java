package com.nigames.jbdd.service.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class SystemUserDetails implements UserDetails {

    private static final long serialVersionUID = -7500665954576073209L;

    private static final Collection<Authority> ROLES = new ArrayList<>();

    private static final String SYSTEM_USER_ROLE = "ROLE_SYSTEM";
    private static final String SYSTEM_USER_USERNAME = "SYSTEM";
    private static final String SYSTEM_USER_PASSWORD = "SYSTEM";

    public SystemUserDetails() {
        ROLES.add(new Authority(SYSTEM_USER_ROLE));
    }

    @SuppressWarnings("SuspiciousGetterSetter")
    @Override
    public Collection<Authority> getAuthorities() {
        return ROLES;
    }

    @SuppressWarnings("SuspiciousGetterSetter")
    @Override
    public String getPassword() {
        return SYSTEM_USER_PASSWORD;
    }

    @SuppressWarnings("SuspiciousGetterSetter")
    @Override
    public String getUsername() {
        return SYSTEM_USER_USERNAME;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

}
