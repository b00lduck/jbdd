package com.nigames.jbdd.domain.entities;

import com.nigames.jbdd.domain.entities.facet.CanBeEnabledEntityFacet;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedBuildingEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedBuyableEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedResourceEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedTechnologyEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Database entity for Players. This represents a game character.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player")
@NamedQueries({
        @NamedQuery(name = PlayerEntity.NQ_UNUSED_SORTED_BY_ID,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=null ORDER BY p.id"),
        @NamedQuery(name = PlayerEntity.NQ_UNUSED_SORTED_BY_ID_DESC,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=null ORDER BY p.id DESC"),

        @NamedQuery(name = PlayerEntity.NQ_UNUSED_SORTED_BY_ENABLED,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=null ORDER BY p.enabled"),
        @NamedQuery(name = PlayerEntity.NQ_UNUSED_SORTED_BY_ENABLED_DESC,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=null ORDER BY p.enabled DESC"),

        @NamedQuery(name = PlayerEntity.NQ_UNUSED_SORTED_BY_NICKNAME,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=null ORDER BY p.nickname"),
        @NamedQuery(name = PlayerEntity.NQ_UNUSED_SORTED_BY_NICKNAME_DESC,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=null ORDER BY p.nickname DESC"),

        @NamedQuery(name = PlayerEntity.NQ_BY_USER_SORTED_BY_ID,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=:userid ORDER BY p.id"),
        @NamedQuery(name = PlayerEntity.NQ_BY_USER_SORTED_BY_ID_DESC,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=:userid ORDER BY p.id DESC"),

        @NamedQuery(name = PlayerEntity.NQ_BY_USER_SORTED_BY_NICKNAME,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=:userid ORDER BY p.nickname"),
        @NamedQuery(name = PlayerEntity.NQ_BY_USER_SORTED_BY_NICKNAME_DESC,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=:userid ORDER BY p.nickname DESC"),

        @NamedQuery(name = PlayerEntity.NQ_BY_USER_SORTED_BY_ENABLED,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=:userid ORDER BY p.enabled"),
        @NamedQuery(name = PlayerEntity.NQ_BY_USER_SORTED_BY_ENABLED_DESC,
                query = "SELECT p FROM PlayerEntity p WHERE p.user.id=:userid ORDER BY p.id DESC"),

        @NamedQuery(name = PlayerEntity.NQ_COUNT_BY_USER_ID,
                query = "SELECT COUNT(id) FROM PlayerEntity WHERE user.id=:userid"),
        @NamedQuery(name = PlayerEntity.NQ_COUNT_UNUSED,
                query = "SELECT COUNT(id) FROM PlayerEntity WHERE user.id=null"),

        @NamedQuery(name = PlayerEntity.NQ_BY_NICKNAME,
                query = "SELECT p FROM PlayerEntity p WHERE p.nickname=:nickname")})

public class PlayerEntity extends IdentifyableEntityFacetImpl implements CanBeEnabledEntityFacet {

    public static final String NQ_UNUSED_SORTED_BY_ID = "PlayerEntity.findAllUnusedSortedById";
    public static final String NQ_UNUSED_SORTED_BY_ID_DESC = "PlayerEntity.findAllUnusedSortedByIdDesc";
    public static final String NQ_UNUSED_SORTED_BY_ENABLED = "PlayerEntity.findAllUnusedSortedByEnabled";
    public static final String NQ_UNUSED_SORTED_BY_ENABLED_DESC = "PlayerEntity.findAllUnusedSortedByEnabledDesc";
    public static final String NQ_UNUSED_SORTED_BY_NICKNAME = "PlayerEntity.findAllUnusedSortedByNickname";
    public static final String NQ_UNUSED_SORTED_BY_NICKNAME_DESC = "PlayerEntity.findAllUnusedSortedByNicknameDesc";
    public static final String NQ_BY_USER_SORTED_BY_ID = "PlayerEntity.findByUserSortedById";
    public static final String NQ_BY_USER_SORTED_BY_ID_DESC = "PlayerEntity.findByUserSortedByIdDesc";
    public static final String NQ_BY_USER_SORTED_BY_NICKNAME = "PlayerEntity.findByUserSortedByNickname";
    public static final String NQ_BY_USER_SORTED_BY_NICKNAME_DESC = "PlayerEntity.findByUserSortedByNicknameDesc";
    public static final String NQ_BY_USER_SORTED_BY_ENABLED = "PlayerEntity.findByUserSortedByEnabled";
    public static final String NQ_BY_USER_SORTED_BY_ENABLED_DESC = "PlayerEntity.findByUserSortedByEnabledDesc";
    public static final String NQ_COUNT_BY_USER_ID = "PlayerEntity.countByUserId";
    public static final String NQ_COUNT_UNUSED = "PlayerEntity.countUnused";
    public static final String NQ_BY_NICKNAME = "PlayerEntity.findPlayerByNickname";
    /**
     * Resources of this Player represented as a List of {@link PlayerAssignedResourceEntity}.
     */
    @OneToMany(mappedBy = "id.playerId")
    @Fetch(FetchMode.SELECT)
    private final List<PlayerAssignedResourceEntity> playerResourceList =
            new ArrayList<>();
    /**
     * Buildings of this Player represented as a List of {@link com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedBuildingEntity}.
     */
    @OneToMany(mappedBy = "player", targetEntity = PlayerAssignedBuyableEntity.class)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "DTYPE='PlayerBuilding'")
    private final List<PlayerAssignedBuildingEntity> playerBuildingList =
            new ArrayList<>();
    /**
     * Technologies of this Player represented as a List of {@link com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedTechnologyEntity}.
     */
    @OneToMany(mappedBy = "player", targetEntity = PlayerAssignedBuyableEntity.class)
    @Fetch(FetchMode.SELECT)
    @Where(clause = "DTYPE='PlayerTechnology'")
    private final List<PlayerAssignedTechnologyEntity> playerTechnologyList =
            new ArrayList<>();

    /**
     * Nickname of the playerSubItem.
     */
    @NotNull
    private String nickname;
    /**
     * {@link UserEntity} object that owns the playerSubItem.
     */
    @ManyToOne
    private UserEntity user;

    /**
     * Enabled flag.
     */
    private Boolean enabled;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(final UserEntity user) {
        this.user = user;
    }

    public List<PlayerAssignedResourceEntity> getPlayerResourceList() {
        return playerResourceList;
    }

    public List<PlayerAssignedBuildingEntity> getPlayerBuildingList() {
        return playerBuildingList;
    }

    public List<PlayerAssignedTechnologyEntity> getPlayerTechnologyList() {
        return playerTechnologyList;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    // TODO: equals, hashCode, toString

}
