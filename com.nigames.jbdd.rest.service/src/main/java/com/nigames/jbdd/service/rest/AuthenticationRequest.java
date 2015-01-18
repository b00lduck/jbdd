package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.AuthenticationRequestInterface;
import com.nigames.jbdd.rest.dto.AuthenticationInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;

@Component
@Path("/authentication")
public class AuthenticationRequest implements AuthenticationRequestInterface {

    private static final Logger LOG = LoggerFactory
            .getLogger(AuthenticationRequest.class);

    @Override
    @GET
    @Path("/")
    public AuthenticationInfo getAuthStatus() {
        LOG.info("getAuthStatus");

        final AuthenticationInfo authInfo = new AuthenticationInfo();

        final SecurityContext context = SecurityContextHolder.getContext();
        final Authentication auth = context.getAuthentication();

        if ((null != auth) && !(auth instanceof AnonymousAuthenticationToken)) {
            final UserDetails userDetails = (UserDetails) auth.getPrincipal();
            authInfo.setUsername(userDetails.getUsername());

            authInfo.setRoles(new ArrayList<>());
            for (final GrantedAuthority ga : userDetails.getAuthorities()) {
                authInfo.getRoles().add(ga.getAuthority());
            }

        }

        return authInfo;
    }

    @Override
    @DELETE
    @Path("/")
    public void logout() {
        LOG.info("logout");
        SecurityContextHolder.clearContext();
    }
}
