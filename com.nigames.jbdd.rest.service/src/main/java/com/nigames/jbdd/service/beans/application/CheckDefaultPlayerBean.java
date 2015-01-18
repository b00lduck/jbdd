package com.nigames.jbdd.service.beans.application;

import com.nigames.jbdd.domain.entities.auth.UserEntity;

/**
 * {@link CheckDefaultPlayerBean} implementation
 * <p>
 * This bean checks if a playerSubItem {@link UserEntity} exists in the database. If no
 * such user exists, it is created. The user will be in disabled state because
 * it is only there to satisfy the foreign key constraint of the playerSubItem object.
 * This bean is configured in
 * {@link com.nigames.jbdd.service.config.dev.CreateDefaultUsersConfig} where
 * defaultPlayerUsername and defaultPlayerPassword are set.
 */
public class CheckDefaultPlayerBean extends AbstractCheckUserBean {

    public void doChecks() {
        checkUserExists(Boolean.FALSE);
        checkUserInactive();
        checkPlayerRoles();

        checkPlayerExists(Boolean.FALSE);
        checkPlayerInactive();
    }

}
