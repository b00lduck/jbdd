package com.nigames.jbdd.service.service.user;

import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.rest.dto.UserRoleEnum;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Nullable;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * UserService interface.
 *
 * @author Daniel
 * @see UserServiceImpl
 */
@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@PreAuthorize(FORBID_ALL)
public interface UserService extends AbstractDtoServiceInterface<User> {

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User create(final User dto);

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User update(final Long id, final User dto);

    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User create(final User dto, final boolean sendPassword);

    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User update(final Long id, final User dto, final boolean sendPassword);

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    void delete(final Long id);

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    ResultList<User> findAll(final LimitParams limitParams, final SortParams sortParams);

    @Override
    @PreAuthorize(HAS_ROLE_ADMIN_USER)
    User findById(final Long entityId);

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
