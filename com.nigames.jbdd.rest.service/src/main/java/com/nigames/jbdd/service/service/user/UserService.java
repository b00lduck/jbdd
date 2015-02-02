package com.nigames.jbdd.service.service.user;

import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.rest.dto.UserRoleEnum;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Nullable;
import java.util.List;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * UserService interface.
 *
 * @author Daniel
 * @see UserServiceImpl
 */
@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@PreAuthorize(FORBID_ALL)
public interface UserService extends AbstractDtoServiceInterface<User, UserEntity> {

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User create(final User dto);

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User update(final long id, final User dto);

    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User create(final User dto, final boolean sendPassword);

    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User update(final long id, final User dto, final boolean sendPassword);

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    void delete(final long id);

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    long getCount();

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    List<User> findAll(final LimitParams limitParams, final SortParams sortParams);

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User findById(final long entityId);

    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    void removePlayer(final long userId, final long playerId);

    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    void addPlayer(final long userId, final long playerId);

    @Nullable
    @PreAuthorize(HAS_ROLE_SYSTEM)
    User findByUsername(final String username);

    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    boolean isUsernameExisting(final String username);

    @PreAuthorize(HAS_ROLE_SYSTEM)
    void addRole(final long userId, final UserRoleEnum role);

    @PreAuthorize(HAS_ROLE_SYSTEM)
    void removeAllRoles(final long userId);

}
