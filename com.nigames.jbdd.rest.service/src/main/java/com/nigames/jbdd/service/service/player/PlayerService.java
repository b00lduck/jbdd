package com.nigames.jbdd.service.service.player;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Nullable;
import java.util.List;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;
import static com.nigames.jbdd.service.service.SecurityElConstants.FORBID_ALL;

/**
 * PlayerService interface.
 *
 * @author Daniel
 * @see PlayerServiceImpl
 */
@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@PreAuthorize(HAS_ROLE_ADMIN_PLAYER)
public interface PlayerService extends AbstractDtoServiceInterface<Player, PlayerEntity> {

    @Override
    @PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_PLAYER)
    Player create(Player dto);

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_PLAYER)
	Player update(final long id, final Player dto);

    List<Player> findByUserId(final long userId, final LimitParams limitParams, final SortParams sortParams);

    long getCountByUserId(final long userId);

    List<Player> findAllUnused(final LimitParams limitParams, final SortParams sortParams);

    long getCountUnused();

    @Nullable
    @PreAuthorize(HAS_ROLE_SYSTEM)
    Player findByNickname(final String nickname);

    @PreAuthorize(HAS_ROLE_SYSTEM)
    List<Player> findByUserId(final long userId);


}
