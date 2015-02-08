package com.nigames.jbdd.service.service.user;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.rest.dto.UserRoleEnum;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.UserConversionService;
import com.nigames.jbdd.service.repository.PlayerRepository;
import com.nigames.jbdd.service.repository.UserRepository;
import com.nigames.jbdd.service.rest.exceptionprovider.ContentNotFoundException;
import com.nigames.jbdd.service.rest.exceptionprovider.EmailAlreadyInUseException;
import com.nigames.jbdd.service.rest.exceptionprovider.InsufficientPermissionsException;
import com.nigames.jbdd.service.rest.exceptionprovider.UsernameAlreadyInUseException;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.service.service.RandomPasswordGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;

/**
 * UserService implementation.
 *
 * @author Daniel
 * @see UserServiceImpl
 */
@Service
public class UserServiceImpl extends AbstractRepositoryBackedService<UserEntity, Long, User>
		implements UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserConversionService userConversionService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private RandomPasswordGenerator randomPasswordGenerator;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static void sendPasswordViaEmail(final User user) {
		LOG.warn("Email sending not implemented.");
		LOG.info("Email: {} {} {}", user.getUsername(), user.getEmail(), user.getPassword());
	}

	@Override
	protected UserRepository getRepository() {
		return userRepository;
	}

	@Override
	protected ConversionServiceInterface<UserEntity, User> getConversionService() {
		return userConversionService;
	}

    @Override
    @Transactional
    public void addRole(final long userId, final UserRoleEnum role) {
	    final UserEntity userEntity = userRepository.findOne(userId);
	    addRole(userEntity, role);
    }

    @Transactional
    private void addRole(final UserEntity userEntity, final UserRoleEnum role) {
        if (!userEntity.getUserRoleList().contains(role)) {
            LOG.info("Adding role {} to user {}", role, userEntity.getUsername());
            userEntity.getUserRoleList().add(role);
        }
    }

    @Override
    @Transactional
    public void removeAllRoles(final long userId) {
	    final UserEntity userEntity = userRepository.findOne(userId);
	    removeAllRoles(userEntity);
    }

    @Override
    @Transactional
    public void removePlayer(final long userId, final long playerId) {
	    final UserEntity userEntity = userRepository.findOne(userId);

        for (final PlayerEntity playerEntity : userEntity.getPlayerList()) {
            if (playerEntity.getId() == playerId) {
                userEntity.getPlayerList().remove(playerEntity);
                playerEntity.setUser(null);
                //noinspection BreakStatement
                break;
            }
        }
    }

    @Override
    @Transactional
    public void addPlayer(final long userId, final long playerId) {
	    final UserEntity userEntity = userRepository.findOne(userId);

        if (userEntity.getUserRoleList().contains(UserRoleEnum.ROLE_PLAYER)) {
	        final PlayerEntity playerEntity = playerRepository.findOne(playerId);
	        userEntity.getPlayerList().add(playerEntity);
            playerEntity.setUser(userEntity);
        } else {
            LOG.error("User {} lacks {} role, not creating player", userEntity.getUsername(), UserRoleEnum.ROLE_PLAYER);
            //noinspection StringConcatenation
            throw new InsufficientPermissionsException("Add player to user failed: User is lacking role " + UserRoleEnum.ROLE_PLAYER);
        }
    }

    @Nullable
    @Override
    @Transactional
    public User findByUsername(final String username) {

	    final UserEntity entity = userRepository.findByUsername(username);

	    if (entity == null) {
		    throw new ContentNotFoundException();
	    }

	    return userConversionService.convertToDtoWithPassword(entity);
    }

	@Override
	@Transactional
    public boolean isUsernameExisting(final String username) {
		UserEntity entity = userRepository.findByUsername(username);
		return (entity != null);
	}

    @Transactional
    public boolean isEmailExisting(final String email) {
	    UserEntity entity = userRepository.findByEmail(email);
	    return (entity != null);
    }

    @Override
    @Transactional
    public User create(final User user, final boolean sendPassword) {

        // TODO: validation for password etc
        LOG.info("user {} is being created", user.getUsername());

        if ((null == user.getPassword()) || user.getPassword().isEmpty()) {
            user.setPassword(createRandomPassword());
        }

        setUserPassword(user, sendPassword);

	    final User ret = super.create(user);

        if (null != user.getRoles()) {
            for (final UserRoleEnum role : user.getRoles()) {
                addRole(ret.getId(), role);
            }
        }

        return ret;
    }

	@Override
	@Transactional
	public User create(final User user) {
		throw new IllegalArgumentException("please call create(User user, boolean sendPassword)");
	}


    private void setUserPassword(final User user, final boolean sendPassword) {
        if (sendPassword) {
	        LOG.info("user {} will get its new password via email", user.getUsername());
	        sendPasswordViaEmail(user);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    @Transactional
    public User update(final Long id, final User user, final boolean sendPassword) {

        // TODO: validation for password etc

        LOG.info("user {} is being updated", user.getUsername());

        // only update password if its present in the dto
        if (null != user.getPassword()) {

            if (user.getPassword().isEmpty()) {
                user.setPassword(createRandomPassword());
                LOG.info("user {} has empty password, creating random", user.getUsername());
            }

            setUserPassword(user, sendPassword);

        }

        removeAllRoles(id);

        if (null != user.getRoles()) {
            for (final UserRoleEnum role : user.getRoles()) {
                addRole(id, role);
            }
        }

	    // get original DTO
	    final User originalUser = findById(id);

        if (!originalUser.getUsername().equals(user.getUsername())) {
            // Username has changed
            if (isUsernameExisting(user.getUsername())) {
                throw new UsernameAlreadyInUseException(user.getUsername());
            }

        }

        if (!originalUser.getEmail().equals(user.getEmail())) {
            // Email has changed
            if (isEmailExisting(user.getEmail())) {
                throw new EmailAlreadyInUseException(user.getEmail());
            }

        }

	    return update(id, user);
    }

    private String createRandomPassword() {
        return randomPasswordGenerator.getRandomPassword();
    }

    @Transactional
    private void removeAllRoles(final UserEntity userEntity) {
        userEntity.getUserRoleList().clear();
    }

}
