package com.nigames.jbdd.service.beans.application;

import com.nigames.jbdd.domain.entities.auth.UserEntity;

/**
 * {@link CheckAdminUserBean} implementation
 * <p>
 * This bean checks if a admin {@link UserEntity} exists in the database. If no
 * such user exists, it is created and a
 * {@link com.nigames.jbdd.domain.entities.auth.UserRoleEntity} ROLE_ADMIN and
 * {@link com.nigames.jbdd.domain.entities.auth.UserRoleEntity} ROLE_PLAYER is added
 * for the new {@link UserEntity}. This bean is configured in
 * {@link com.nigames.jbdd.service.config.dev.CreateDefaultUsersConfig} where
 * defaultAdminUsername and defaultAdminPassword are setLang.
 */
public class CheckAdminUserBean extends AbstractCheckUserBean {

    public void doChecks() {
        checkUserExists(Boolean.TRUE);
        checkUserActive();
        checkAdminRoles();
    }

}
