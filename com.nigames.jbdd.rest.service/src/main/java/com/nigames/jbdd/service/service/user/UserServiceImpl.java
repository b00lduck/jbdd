package com.nigames.jbdd.service.service.user;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.domain.entities.auth.UserRoleEntity;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.rest.dto.UserRole;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.UserConversionService;
import com.nigames.jbdd.service.rest.exceptionprovider.EmailAlreadyInUseException;
import com.nigames.jbdd.service.rest.exceptionprovider.InsufficientPermissionsException;
import com.nigames.jbdd.service.rest.exceptionprovider.UsernameAlreadyInUseException;
import com.nigames.jbdd.service.service.AbstractDtoService;
import com.nigames.jbdd.service.service.RandomPasswordGenerator;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import com.nigames.jbdd.service.service.querystrategy.UserQueryStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

import static com.nigames.jbdd.rest.dto.UserRole.ROLE_PLAYER;

/**
 * UserService implementation.
 *
 * @author Daniel
 * @see UserServiceImpl
 */
@SuppressWarnings("ClassWithTooManyMethods")
@Service
public final class UserServiceImpl extends AbstractDtoService<User, UserEntity> implements
        UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private transient PasswordEncoder passwordEncoder;

    @Autowired
    private transient UserConversionService userConversionService;

    @Autowired
    private transient UserQueryStrategy userSortStrategy;

    @Autowired
    private transient RandomPasswordGenerator randomPasswordGenerator;

    private static void sendPasswordViaEmail(final User user) {
        LOG.warn("Email sending not implemented.");
        LOG.info("Email: {} {} {}", user.getUsername(), user.getEmail(), user.getPassword());
    }

    @Override
    @Transactional
    public void delete(final long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    public List<User> findAll(final LimitParams limitParams, final SortParams sortParams) {
        return super.findAll(limitParams, sortParams);
    }

    @Override
    @Transactional
    public List<User> findAll(final LimitParams limitParams, final SortParams sortParams,
                              final QueryStrategy<UserEntity> queryStrategy, final Object... queryParams) {
        return super.findAll(limitParams, sortParams, queryStrategy, queryParams);
    }

    @Override
    @Transactional
    public User findById(final long entityId) {
        return super.findById(entityId);
    }

    @Override
    @Transactional
    public Long getCount() {
        return super.getCount();
    }

    @Override
    @Transactional
    public void addRole(final long userId, final String role) {
        final UserEntity userEntity = getEntityManager().find(UserEntity.class, userId);
        addRole(userEntity, role);
    }

    // @Override
    // @Transactional
    // public final void removeRole(final long userId, final String role) {
    // UserEntity userEntity = getEntityManager().find(UserEntity.class,
    // userId);
    //
    // UserRoleEntity playerRole = new UserRoleEntity(role, userEntity);
    //
    // if (userEntity.getUserRoleList().contains(playerRole)) {
    // LOG.info("Removing role " + role + " from user "
    // + userEntity.getUsername());
    // TypedQuery<UserRoleEntity> query = getEntityManager()
    // .createNamedQuery("findUserRoleByRoleAndUser",
    // UserRoleEntity.class);
    // query.setParameter("user", userEntity);
    // query.setParameter("user_role", role);
    // List<UserRoleEntity> res = query.getResultList();
    // for (UserRoleEntity r : res) {
    // userEntity.getUserRoleList().remove(r);
    // getEntityManager().remove(r);
    // }
    // }
    // }

    @Transactional
    private void addRole(final UserEntity userEntity, final String role) {
        final UserRoleEntity playerRole = UserRoleEntity.createFromRoleAndUser(role, userEntity);

        if (!userEntity.getUserRoleList().contains(playerRole)) {
            LOG.info("Adding role {} to user {}", role, userEntity.getUsername());
            final UserRoleEntity userRole = UserRoleEntity.createFromRoleAndUser(role, userEntity);
            getEntityManager().persist(userRole);
            userEntity.getUserRoleList().add(userRole);
        }
    }

    @Override
    @Transactional
    public void removeAllRoles(final long userId) {
        final UserEntity userEntity = getEntityManager().find(UserEntity.class, userId);
        removeAllRoles(userEntity);
    }

    @Override
    @Transactional
    public void removePlayer(final long userId, final long playerId) {
        final UserEntity userEntity = getEntityManager().find(UserEntity.class, userId);

        for (final PlayerEntity playerEntity : userEntity.getPlayerList()) {
            if (playerEntity.getId() == playerId) {
                userEntity.getPlayerList().remove(playerEntity);
                playerEntity.setUser(null);
                //noinspection BreakStatement
                break;
            }
        }
    }

    // @Transactional
    // private final void setPassword(final long userId, final String password) {
    // UserEntity userEntity = getEntityManager().find(UserEntity.class, userId);
    // LOG.info("Changing password for user " + userEntity.getUsername());
    // userEntity.setPassword(passwordEncoder.encode(password));
    // }

    // /**
    // * Get a list of all enabled {@link UserEntity} that have a specific role.
    // *
    // * @param role
    // * the role i.e. {@link UserRole}.ROLE_ADMIN_SUPER
    // * @return list of {@link UserEntity}
    // */
    // @Override
    // @Transactional
    // public final List<User> findAllEnabledUsersByRole(final String role) {
    // TypedQuery<UserEntity> query = getEntityManager().createNamedQuery(
    // "findEnabledUsersWithPlayerRole", UserEntity.class);
    // query.setParameter("role", role);
    // List<UserEntity> list = query.getResultList();
    //
    // return userConversionService.convertToDto(list);
    // }

    // /**
    // * Get a list of all {@link UserEntity} that have a specific role.
    // *
    // * @param role
    // * the role i.e. {@link UserRole}.ROLE_ADMIN_SUPER
    // * @return list of {@link UserEntity}
    // */
    // @Override
    // @Transactional
    // public final List<User> findAllUsersByRole(final String role) {
    // TypedQuery<UserEntity> q = getEntityManager().createNamedQuery(
    // "findUsersWithPlayerRole", UserEntity.class);
    // q.setParameter("role", role);
    // return userConversionService.convertToDto(q.getResultList());
    // }

    // @Override
    // @Transactional
    // public final List<User> findAllEnabledPlayerUsers() {
    // return findAllEnabledUsersByRole(UserRole.ROLE_PLAYER);
    // }
    //
    // @Override
    // @Transactional
    // public final List<User> findAllPlayerUsers() {
    // return findAllUsersByRole(UserRole.ROLE_PLAYER);
    // }
    //
    // @Override
    // @Transactional
    // public final List<User> findAllEnabledAdminUserUsers() {
    // return findAllEnabledUsersByRole(UserRole.ROLE_ADMIN_USER);
    // }

    @Override
    @Transactional
    public void addPlayer(final long userId, final long playerId) {
        final UserEntity userEntity = getEntityManager().find(UserEntity.class, userId);

        final UserRoleEntity playerRole = UserRoleEntity.createFromRoleAndUser(ROLE_PLAYER, userEntity);

        if (userEntity.getUserRoleList().contains(playerRole)) {
            final PlayerEntity playerEntity = getEntityManager().find(PlayerEntity.class, playerId);

            userEntity.getPlayerList().add(playerEntity);
            playerEntity.setUser(userEntity);
        } else {
            LOG.error("User {} lacks ROLE_PLAYER role, not creating player", userEntity.getUsername());
            throw new InsufficientPermissionsException("Add player to user failed: User is lacking " + ROLE_PLAYER);
        }
    }

    @Nullable
    @Override
    @Transactional
    public User findByUsername(final String username) {
        final Query query = getEntityManager().createNamedQuery(UserEntity.NQ_BY_USERNAME);
        query.setParameter("username", username);

        final UserEntity entity;
        try {
            entity = (UserEntity) query.getSingleResult();
        } catch (NoResultException | EmptyResultDataAccessException ignored) {
            LOG.info("empty result fetched: {} with username={}", UserEntity.NQ_BY_USERNAME, username);
            return null;
        }
        return userConversionService.convertToDtoWithPassword(entity);
    }

    @Transactional
    public boolean doesUsernameExist(final String username) {
        final Query query = getEntityManager().createNamedQuery(UserEntity.NQ_BY_USERNAME);
        query.setParameter("username", username);

        try {
            query.getSingleResult();
        } catch (NoResultException | EmptyResultDataAccessException ignored) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean doesEmailExist(final String email) {
        final Query query = getEntityManager().createNamedQuery(UserEntity.NQ_BY_EMAIL);
        query.setParameter("email", email);

        try {
            query.getSingleResult();
        } catch (NoResultException | EmptyResultDataAccessException ignored) {
            return false;
        }
        return true;
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
            for (final UserRole role : user.getRoles()) {
                addRole(ret.getId(), role.getRoleName());
            }
        }

        return ret;
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
    public User update(final long id, final User user, final boolean sendPassword) {

        // TODO: validation for password etc

        LOG.info("user {} is being updated", user.getUsername());

        // only update password if its present in the dto
        if (null != user.getPassword()) {

            if (user.getPassword() != null && user.getPassword().isEmpty()) {
                user.setPassword(createRandomPassword());
                LOG.info("user {} has empty password, creating random", user.getUsername());
            }

            setUserPassword(user, sendPassword);

        }

        removeAllRoles(id);

        if (null != user.getRoles()) {
            for (final UserRole role : user.getRoles()) {
                addRole(id, role.getRoleName());
            }
        }

        // get original DTO
        final User originalUser = findById(id);

        if (!originalUser.getUsername().equals(user.getUsername())) {
            // Username has changed
            if (doesUsernameExist(user.getUsername())) {
                throw new UsernameAlreadyInUseException(user.getUsername());
            }

        }

        if (!originalUser.getEmail().equals(user.getEmail())) {
            // Email has changed
            if (doesEmailExist(user.getEmail())) {
                throw new EmailAlreadyInUseException(user.getEmail());
            }

        }

        return super.update(id, user);
    }

    private String createRandomPassword() {
        return randomPasswordGenerator.getRandomPassword();
    }

    @SuppressWarnings("RefusedBequest")
    @Override
    public User update(final long id, final User dto) {
        return update(id, dto, false);
    }

    @SuppressWarnings("RefusedBequest")
    @Override
    public User create(final User dto) {
        return create(dto, false);
    }

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

    @Override
    protected ConversionServiceInterface<UserEntity, User> getConversionService() {
        return userConversionService;
    }

    @Override
    protected QueryStrategy<UserEntity> getDefaultQueryStrategy() {
        return userSortStrategy;
    }

    @Transactional
    private void removeAllRoles(final UserEntity userEntity) {
        for (final UserRoleEntity role : userEntity.getUserRoleList()) {
            LOG.info("Removing role {} from user {}", role.getRole(), userEntity.getUsername());
            getEntityManager().remove(role);
        }
        userEntity.getUserRoleList().clear();
    }

}
