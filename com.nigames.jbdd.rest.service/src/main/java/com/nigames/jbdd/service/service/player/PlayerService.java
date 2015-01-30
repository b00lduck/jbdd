package com.nigames.jbdd.service.service.player;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Nullable;
import java.util.List;

import static com.nigames.jbdd.rest.dto.UserRole.ROLE_ADMIN_PLAYER;
import static com.nigames.jbdd.rest.dto.UserRole.ROLE_SYSTEM;

/**
 * PlayerService interface.
 *
 * @author Daniel
 * @see PlayerServiceImpl
 */
@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@PreAuthorize("hasRole('ROLE_EXCLUDE_ALL')")
public interface PlayerService extends AbstractDtoServiceInterface<Player, PlayerEntity> {

    @Override
    @PreAuthorize("hasAnyRole('" + ROLE_SYSTEM + "','" + ROLE_ADMIN_PLAYER + "')")
    Player create(Player dto);

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_PLAYER + "')")
    void delete(long id);

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_PLAYER + "')")
    Player findById(long entityId);

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_PLAYER + "')")
    long getCount();

    @Override
    @PreAuthorize("hasRole('" + ROLE_ADMIN_PLAYER + "')")
    List<Player> findAll(final LimitParams limitParams, final SortParams sortParams);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_PLAYER + "')")
    List<Player> findByUserId(final long userId, final LimitParams limitParams, final SortParams sortParams);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_PLAYER + "')")
    long getCountByUserId(final long userId);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_PLAYER + "')")
    List<Player> findAllUnused(final LimitParams limitParams, final SortParams sortParams);

    @PreAuthorize("hasRole('" + ROLE_ADMIN_PLAYER + "')")
    long getCountUnused();

    @Nullable
    @PreAuthorize("hasRole('" + ROLE_SYSTEM + "')")
    Player findByNickname(final String nickname);

    @PreAuthorize("hasRole('" + ROLE_SYSTEM + "')")
    List<Player> findByUserId(final long userId);

    @Override
    @PreAuthorize("hasAnyRole('" + ROLE_SYSTEM + "','" + ROLE_ADMIN_PLAYER + "')")
    Player update(final long id, final Player dto);

}
