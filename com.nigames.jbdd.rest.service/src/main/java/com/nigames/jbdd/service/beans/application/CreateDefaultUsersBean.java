package com.nigames.jbdd.service.beans.application;

import com.nigames.jbdd.service.auth.SystemUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

/**
 * This bean is executed at application startup.
 *
 * @author Daniel
 */
@Service
class CreateDefaultUsersBean implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * The CheckAdminUserBean.
     */
    @Autowired
    private CheckAdminUserBean checkAdminUserBean;

    /**
     * The CheckDefaultUserBean.
     */
    @Autowired
    private CheckDefaultPlayerBean checkDefaultPlayerBean;

    /**
     * The CheckDummyPlayerBean.
     */
    @Autowired
    private CheckDummyPlayerBean checkDummyPlayerBean;

    @Override
    public void onApplicationEvent(final ContextRefreshedEvent event) {

        final SystemUserDetails sysAcc = new SystemUserDetails();
        final Authentication token =
                new PreAuthenticatedAuthenticationToken(sysAcc, sysAcc.getPassword(),
                        sysAcc.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(token);

        checkAdminUserBean.doChecks();
        checkDefaultPlayerBean.doChecks();
        checkDummyPlayerBean.doChecks();
    }

}
