package com.nigames.jbdd.service.auth;

import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.service.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(final String username) {

        final User user;

        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (null == auth) {

            final SystemUserDetails sysAcc = new SystemUserDetails();
            final Authentication token =
                    new PreAuthenticatedAuthenticationToken(sysAcc, sysAcc.getPassword(),
                            sysAcc.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(token);

            user = userService.findByUsername(username);

            // TODO: finally context delete

            SecurityContextHolder.getContext().setAuthentication(null);

        } else {

            // TODO: check if this code can be reached at all

            final UserDetails sysAcc = new SystemUserDetails();

            //noinspection unchecked,rawtypes
            auth.getAuthorities().addAll((Collection) sysAcc.getAuthorities());

            user = userService.findByUsername(username);

            auth.getAuthorities().removeAll(sysAcc.getAuthorities());

        }

        return new UserDetailsImpl(user);

        // TODO: throw UsernameNotFoundException

    }
}
