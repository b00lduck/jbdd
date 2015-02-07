package com.nigames.jbdd.domain.entities;

import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.domain.entities.facet.CanBeEnabledEntityFacet;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedBuildingEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedBuyableEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedResourceEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedTechnologyEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
public class PlayerEntity extends IdentifyableEntityFacetImpl implements CanBeEnabledEntityFacet {

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
