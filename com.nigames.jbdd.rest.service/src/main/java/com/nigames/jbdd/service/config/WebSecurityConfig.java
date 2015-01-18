package com.nigames.jbdd.service.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring configuration class. Configures HTTP Basic authentication.
 *
 * @author Daniel
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    @SuppressWarnings({"ChainedMethodCall", "ProhibitedExceptionDeclared", "RefusedBequest"})
    protected void configure(final HttpSecurity http) throws Exception {
        // Disable cross site request forgery protection
        http.csrf().disable().httpBasic();
        //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
    }

}
