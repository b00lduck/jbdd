package com.nigames.jbdd.service.service.user;

import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Nullable;
import java.util.List;

import static com.nigames.jbdd.rest.dto.UserRole.ROLE_ADMIN_USER;
import static com.nigames.jbdd.rest.dto.UserRole.ROLE_SYSTEM;

/**
 * UserService interface.
 *
 * @author Daniel
 * @see UserServiceImpl
 */
@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@PreAuthorize("hasRole('ROLE_EXCLUDE_ALL')")
public interface UserService extends AbstractDtoServiceInterface<User, UserEntity> {

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    User create(final User dto);

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    User update(final long id, final User dto);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    User create(final User dto, final boolean sendPassword);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    User update(final long id, final User dto, final boolean sendPassword);

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    void delete(final long id);

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    Long getCount();

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    List<User> findAll(final LimitParams limitParams, final SortParams sortParams);

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    User findById(final long entityId);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    void removePlayer(final long userId, final long playerId);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    void addPlayer(final long userId, final long playerId);

    @Nullable
    @PreAuthorize("hasRole('" + ROLE_SYSTEM + "')")
    User findByUsername(final String username);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_USER + "')")
    boolean doesUsernameExist(final String username);

    @PreAuthorize("hasRole('" + ROLE_SYSTEM + "')")
    void addRole(final long userId, final String role);

    @PreAuthorize("hasRole('" + ROLE_SYSTEM + "')")
    void removeAllRoles(final long userId);

}
