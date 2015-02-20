package com.nigames.jbdd.service.service.player;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.PlayerConversionService;
import com.nigames.jbdd.service.repository.PlayerRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.SortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import java.util.List;

/**
 * PlayerService implementation.
 *
 * @author Daniel
 * @see PlayerServiceImpl
 */
@Service
public class PlayerServiceImpl extends AbstractRepositoryBackedService<PlayerEntity, Long, Player>
		implements PlayerService {

	private static final Logger LOG = LoggerFactory.getLogger(PlayerServiceImpl.class);

    @Autowired
    private PlayerConversionService playerConversionService;

	@Autowired
	private PlayerRepository playerRepository;

	@Override
	protected PlayerRepository getRepository() {
		return playerRepository;
	}

	@Override
	protected ConversionServiceInterface<PlayerEntity, Player> getConversionService() {
		return playerConversionService;
	}

    @Nullable
    @Override
    @Transactional
    public Player findByNickname(final String nickname) {
	    try {
		    final PlayerEntity playerEntity = playerRepository.findByNickname(nickname);
		    return playerConversionService.convertToDto(playerEntity);
	    } catch (final IncorrectResultSizeDataAccessException ignored) {
		    LOG.error("There ist more that one player with nickname {}");

		    // TODO: throw execption
		    return null;
	    }
    }

    @Override
    @Transactional
    public final ResultList<Player> findByUserId(final long userId) {
	    final List<PlayerEntity> playerEntityList = playerRepository.findByUserId(userId);
        final List<Player> dtoList = playerConversionService.convertToDto(playerEntityList);

        return ResultList.create(dtoList);
    }

    @Override
    @Transactional
    public final ResultList<Player> findByUserId(final long userId, final LimitParams limitParams, final SortParams sortParams) {
	    final Pageable pageable = createPageable(limitParams, sortParams);

        final List<PlayerEntity> playerEntityList = playerRepository.findByUserId(userId, pageable).getContent();

        final List<Player> dtoList = playerConversionService.convertToDto(playerEntityList);
        final long total = playerRepository.countByUserId(userId);

	    return ResultList.create(dtoList, total);
    }

    @Override
    @Transactional
    public ResultList<Player> findAllUnused(final LimitParams limitParams, final SortParams sortParams) {
	    final Pageable pageable = createPageable(limitParams, sortParams);

        final List<PlayerEntity> playerEntityList = playerRepository.findUnused(pageable).getContent();

        final List<Player> dtoList = playerConversionService.convertToDto(playerEntityList);
        final long total = playerRepository.countUnused();

        return ResultList.create(dtoList, total);
    }
}
