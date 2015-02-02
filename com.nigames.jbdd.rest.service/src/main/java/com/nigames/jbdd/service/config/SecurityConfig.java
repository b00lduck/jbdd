package com.nigames.jbdd.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring configuration. Configures the authentication service of spring security and activates
 * global method security.
 *
 * @author Daniel
 */
@PropertySource("classpath:security.properties")
@EnableGlobalMethodSecurity(mode = AdviceMode.ASPECTJ, prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends GlobalMethodSecurityConfiguration {

    @Value("#{ environment['jbdd.security.bcryptStrength'] }")
    private Integer bcryptStrength;

    @Autowired
    private PasswordEncoder myPasswordEncoder;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * Register the {@link UserDetailsService} as auth source. This is called by spring
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception
     */
    @SuppressWarnings("ProhibitedExceptionDeclared") // The interface wants me to throw "Exception"!
    @Autowired
    public void registerAuthentication(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(myPasswordEncoder);
    }

    /**
     * Construct the PasswordEncoder bean. This is used to encrypt authentication passwords in
     * spring security. In this case, a BCrypt with configurable strength is used.
     *
     * @return BCrypt password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(bcryptStrength);
    }

}
