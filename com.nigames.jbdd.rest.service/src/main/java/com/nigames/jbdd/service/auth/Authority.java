package com.nigames.jbdd.service.auth;

import com.nigames.jbdd.rest.dto.UserRoleEnum;
import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("ClassNamePrefixedWithPackageName")
class Authority implements GrantedAuthority {

    private static final long serialVersionUID = 1L;

    private final String authority;

    Authority(final String role) {
        authority = role;
    }

    Authority(final UserRoleEnum userRole) {
        authority = userRole.toString();
    }

    @Override
    public String getAuthority() {
        return authority;
    }

}
