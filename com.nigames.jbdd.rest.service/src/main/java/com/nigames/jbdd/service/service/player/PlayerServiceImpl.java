package com.nigames.jbdd.service.service.player;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.PlayerConversionService;
import com.nigames.jbdd.service.service.AbstractDtoService;
import com.nigames.jbdd.service.service.querystrategy.*;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;


/**
 * PlayerService implementation.
 *
 * @author Daniel
 * @see PlayerServiceImpl
 */
@Service
public class PlayerServiceImpl extends AbstractDtoService<Player, PlayerEntity> implements
        PlayerService {

    private static final Logger LOG = LoggerFactory
            .getLogger(PlayerServiceImpl.class);

    @Autowired
    private transient PlayerConversionService playerConversionService;

    @Autowired
    private transient PlayerQueryStrategy playerSortStrategy;

    @Autowired
    private transient UserPlayerQueryStrategy userPlayerQueryStrategy;

    @Autowired
    private transient PlayerChooseQueryStrategy unusedPlayerQueryStrategy;

    @Override
    @Transactional
    public Player create(final Player dto) {
        return super.create(dto);
    }

    @Override
    @Transactional
    public Player update(final long id, final Player dto) {
        return super.update(id, dto);
    }

    @Override
    @Transactional
    public void delete(final long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    public List<Player> findAll(final LimitParams limitParams, final SortParams sortParams) {
        return super.findAll(limitParams, sortParams);
    }

    @Override
    @Transactional
    protected List<Player> findAll(final LimitParams limitParams, final SortParams sortParams, final QueryStrategy<PlayerEntity> queryStrategy,
                                   final Object... queryParams) {
        return super.findAll(limitParams, sortParams, queryStrategy, queryParams);
    }

    @Override
    @Transactional
    public Player findById(final long entityId) {
        return super.findById(entityId);
    }

    @Override
    @Transactional
    public long getCount() {
        return super.getCount();
    }

    /*
    @Override
    @Transactional
    public void createPlayer(final long userId, final Player player) {

        final UserEntity userEntity = getEntityManager().find(UserEntity.class, userId);

        final UserRoleEntity playerRole = UserRoleEntity.createFromRoleAndUser(ROLE_PLAYER, userEntity);

        if (userEntity.getUserRoleList().contains(playerRole)) {
            try {
                final PlayerEntity playerEntity = playerConversionService.convertToEntity(player);
                playerEntity.setUser(userEntity);
                getEntityManager().persist(playerEntity);
                userEntity.getPlayerList().add(playerEntity);
            } catch (final RuntimeException e) {
                LOG.error("createPlayer failed");
                throw new PlayerCreationFailedException("Create player failed because of exception", e);
            }
        } else {
            LOG.error("User {} lacks ROLE_PLAYER role, not creating player", userEntity.getUsername());
            throw new PlayerCreationFailedException("Create player failed: lacking ROLE_PLAYER");
        }
    }
    */

    @Nullable
    @Override
    @Transactional
    public Player findByNickname(final String nickname) {

        final TypedQuery<PlayerEntity> query =
                getEntityManager().createNamedQuery(PlayerEntity.NQ_BY_NICKNAME, PlayerEntity.class);

        query.setParameter("nickname", nickname);

        final PlayerEntity playerEntity;
        try {
            playerEntity = query.getSingleResult();
        } catch (NoResultException | EmptyResultDataAccessException ignored) {
            LOG.info("empty result fetched: findPlayerByNickname with nickname={}", nickname);
            return null;
        }
        return playerConversionService.convertToDto(playerEntity);
    }

    @Override
    @Transactional
    public final List<Player> findByUserId(final long userId) {
        return findByUserId(userId, LimitParams.create(null, null), SortParams.create(null, null));
    }

    @Override
    @Transactional
    public final List<Player> findByUserId(final long userId, final LimitParams limitParams, final SortParams sortParams) {
        return findAll(limitParams, sortParams, userPlayerQueryStrategy, userId);
    }

    @Override
    @Transactional
    public final long getCountByUserId(final long userId) {
        final TypedQuery<Long> query =
                getEntityManager().createNamedQuery("PlayerEntity.countByUserId", Long.class);
        query.setParameter("userid", userId);
        return query.getSingleResult();
    }

    @Override
    @Transactional
    public List<Player> findAllUnused(final LimitParams limitParams, final SortParams sortParams) {
        return findAll(limitParams, sortParams, unusedPlayerQueryStrategy);
    }

    @Override
    @Transactional
    public final long getCountUnused() {
        final TypedQuery<Long> query =
                getEntityManager().createNamedQuery("PlayerEntity.countUnused", Long.class);
        return query.getSingleResult();
    }

    // @Override
    // @Transactional
    // public final List<Player> findAllUnused() {
    // return findAllUnused(null, null, null, null);
    // }

    // @Transactional
    // public List<PlayerBuilding> getAllPlayerBuildings(final long playerId) {
    // return playerBuildingSubService.getAll(playerId);
    // }

    // @Transactional
    // public List<Building> getAllAddablePlayerBuildings(final long playerId) {
    //
    // // Get all enabled buildings
    // final TypedQuery<BuildingEntity> query = getEntityManager()
    // .createNamedQuery("findAllEnabledBuildings",
    // BuildingEntity.class);
    // final List<BuildingEntity> ret = query.getResultList();
    //
    // PlayerEntity playerEntity = getEntityManager().find(PlayerEntity.class,
    // playerId);
    //
    // for (final PlayerBuildingEntity b : playerEntity
    // .getPlayerBuildingList()) {
    // if (!b.getBuyable().isMulti()) {
    // ret.remove(b.getBuyable());
    // }
    // }
    //
    // return buildingConversionService.convertToDto(ret);
    // }

    // @Transactional
    // public void updatePlayerTechnology(Long playerTechnologyId,
    // final PlayerTechnology playerTechnology) {
    // playerTechnologySubService.update(playerTechnologyId, playerTechnology);
    // }

    // @Transactional
    // public void deletePlayerTechnology(final Long playerTechnologyId) {
    // playerTechnologySubService.remove(playerTechnologyId);
    // }

    // @Transactional
    // public void addPlayerTechnology(final long playerId,
    // final PlayerTechnology playerTechnology) {
    // playerTechnologySubService.create(playerId, playerTechnology);
    // }

    // @Transactional
    // public List<PlayerTechnology> getAllPlayerTechnologies(final Long
    // playerId) {
    // return playerTechnologySubService.getAll(playerId);
    // }

    // @Transactional
    // public List<Technology> getAllAddablePlayerTechnologies(final Long
    // playerId) {
    //
    // // Get all enabled technologies
    // final TypedQuery<TechnologyEntity> query = getEntityManager()
    // .createNamedQuery("findAllEnabledTechnologies",
    // TechnologyEntity.class);
    // final List<TechnologyEntity> ret = query.getResultList();
    //
    // PlayerEntity attachedPlayer = getEntityManager().find(
    // PlayerEntity.class, playerId);
    //
    // for (final PlayerTechnologyEntity b : attachedPlayer
    // .getPlayerTechnologyList()) {
    // ret.remove(b.getBuyable());
    // }
    //
    // return technologyConversionService.convertToDto(ret);
    // }

    // @Transactional
    // public void updatePlayerPeople(Long playerPeopleId,
    // final PlayerPeople playerPeople) {
    // playerPeopleSubService.update(playerPeopleId, playerPeople);
    // }

    // @Transactional
    // public void deletePlayerPeople(final Long playerPeopleId) {
    // playerPeopleSubService.remove(playerPeopleId);
    // }

    // @Transactional
    // public void addPlayerPeople(final long playerId,
    // final PlayerPeople playerPeople) {
    // playerPeopleSubService.create(playerId, playerPeople);
    // }

    // @Transactional
    // public List<PlayerPeople> getAllPlayerPeople(final long playerId) {
    // return playerPeopleSubService.getAll(playerId);
    // }

    // @Transactional
    // public void updatePlayerResource(Long playerResourceId,
    // PlayerResource playerResource) {
    // playerResourceSubService.update(playerResourceId, playerResource);
    // }

    // @Transactional
    // public void deletePlayerResource(Long playerResourceId) {
    // playerResourceSubService.remove(playerResourceId);
    // }

    // @Transactional
    // public void addPlayerResource(Long playerId, PlayerResource
    // playerResource) {
    // playerResourceSubService.create(playerId, playerResource);
    // }

    // @Transactional
    // public List<PlayerResource> getAllPlayerResources(Long playerId) {
    // return playerResourceSubService.getAll(playerId);
    // }

    // @Transactional
    // public void updatePlayerBuilding(Long playerBuildingId,
    // PlayerBuilding playerBuilding) {
    // playerBuildingSubService.update(playerBuildingId, playerBuilding);
    // }

    // @Transactional
    // public void deletePlayerBuilding(Long playerBuildingId) {
    // playerBuildingSubService.remove(playerBuildingId);
    // }

    // @Transactional
    // public void addPlayerBuilding(Long playerId, PlayerBuilding
    // playerBuilding) {
    // playerBuildingSubService.create(playerId, playerBuilding);
    // }

    // @Override
    // @Transactional
    // public List<AbstractResource> getAllAddablePlayerResources(
    // final long playerId) {
    //
    // // Get all enabled goods
    // final TypedQuery<GoodEntity> query = getEntityManager()
    // .createNamedQuery("findAllEnabledGoods", GoodEntity.class);
    // final List<GoodEntity> allGoods = query.getResultList();
    //
    // PlayerEntity playerEntity = getEntityManager().find(PlayerEntity.class,
    // playerId);
    //
    // for (final PlayerResourceEntity r : playerEntity
    // .getPlayerResourceList()) {
    // allGoods.remove(r.getResource());
    // }
    //
    // final List<AbstractResource> ret = new ArrayList<AbstractResource>();
    //
    // for (final GoodEntity goodEntity : allGoods) {
    // ret.add(goodConversionService.convertToDto(goodEntity));
    // }
    //
    // return ret;
    // }

    @Override
    protected Class<PlayerEntity> getEntityClass() {
        return PlayerEntity.class;
    }

    @Override
    protected ConversionServiceInterface<PlayerEntity, Player> getConversionService() {
        return playerConversionService;
    }

    @Override
    protected QueryStrategy<PlayerEntity> getDefaultQueryStrategy() {
        return playerSortStrategy;
    }

}
