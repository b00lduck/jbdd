package com.nigames.jbdd.service.beans.application;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.rest.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import static com.nigames.jbdd.rest.dto.UserRole.ROLE_PLAYER;

/**
 * {@link CheckDummyPlayerBean} implementation
 * <p>
 * This bean checks if a defined number of dummy User and Player
 * exists in the database. If no such object exists, it is created. The
 *
 * @link User} will be in disabled state because it is only there to satisfy the
 * foreign key constraint of the playerSubItem object. This bean is configured in
 * {@link com.nigames.jbdd.service.config.dev.CreateDefaultUsersConfig} where
 * dummyPlayerUsername, dummyPlayerPassword and numberOfDummyPlayers are
 * set.
 */
@SuppressWarnings("CallToNumericToString")
public class CheckDummyPlayerBean extends AbstractCheckUserBean {

    private static final Logger LOG = LoggerFactory
            .getLogger(CheckDummyPlayerBean.class);

    /**
     * The number of dummy {@link PlayerEntity} to be created.
     */
    @SuppressWarnings("FieldHasSetterButNoGetter")
    private transient Integer numberOfDummyPlayers;

    public void doChecks() {
        for (Integer i = 1; i <= numberOfDummyPlayers; i++) {
            try {
                processOneUser(i.toString());
            } catch (final RuntimeException e) {
                LOG.error("Creating of dummy user {} failed: {}", i, e.getMessage());
            }
        }
    }

    public void setNumberOfDummyPlayers(final Integer numberOfDummyPlayers) {
        this.numberOfDummyPlayers = numberOfDummyPlayers;
    }

    @Transactional
    private void processOneUser(final String iStr) {
        final String username = getUsername(iStr);
        final String password = getPassword(iStr);
        final String nickname = getNickname(iStr);
        final String email = getEmail(iStr);

        checkUserExists(username, password, email, Boolean.FALSE);
        checkUserInactive(username);
        checkPlayerRoles(username);

        checkPlayerExists(nickname, username, Boolean.FALSE);
        checkPlayerInactive(nickname);
    }

    @Transactional
    private void checkPlayerRoles(final String playerUsername) {
        LOG.info("Checking roles of the dummy playerSubItem user \"{}\"", playerUsername);
        final User user = userService.findByUsername(playerUsername);
        userService.removeAllRoles(user.getId());
        userService.addRole(user.getId(), ROLE_PLAYER);
    }

}
