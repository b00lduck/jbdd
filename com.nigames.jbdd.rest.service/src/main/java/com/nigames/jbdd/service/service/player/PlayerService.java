package com.nigames.jbdd.service.service.player;

import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.annotation.Nullable;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * PlayerService interface.
 *
 * @author Daniel
 * @see PlayerServiceImpl
 */
@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@PreAuthorize(HAS_ROLE_ADMIN_PLAYER)
public interface PlayerService extends AbstractDtoServiceInterface<Player> {

    @Override
    @PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_PLAYER)
    Player create(Player dto);

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_PLAYER)
	Player update(final Long id, final Player dto);

	@PreAuthorize(HAS_ROLE_SYSTEM)
    ResultList<Player> findByUserId(final long userId);

    ResultList<Player> findByUserId(final long userId, final LimitParams limitParams, final SortParams sortParams);

    ResultList<Player> findAllUnused(final LimitParams limitParams, final SortParams sortParams);

    @Nullable
    @PreAuthorize(HAS_ROLE_SYSTEM)
    Player findByNickname(final String nickname);
}
