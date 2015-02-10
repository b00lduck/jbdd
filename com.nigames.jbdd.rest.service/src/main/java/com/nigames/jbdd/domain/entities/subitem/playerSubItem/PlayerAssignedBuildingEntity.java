package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedBuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedBuyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedEntityFacet;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedEntityFacetImpl;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Database entity for Players {@link com.nigames.jbdd.domain.entities.item.BuildingEntity} objects. This
 * represents the buildings a game character owns.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player_assigned_building")
public class PlayerAssignedBuildingEntity extends IdentifyableEntityFacetImpl implements PlayerAssignedEntityFacet,
        PlayerAssignedBuyableEntityFacet, PlayerAssignedSubItem<Long> {

    @NotNull
    @OneToOne(mappedBy = "identifyableEntityFacet", cascade = CascadeType.ALL)
    private PlayerAssignedEntityFacetImpl playerAssignedEntityFacet;

    @NotNull
    @OneToOne(mappedBy = "identifyableEntityFacet", cascade = CascadeType.ALL)
    private PlayerAssignedBuyableEntityFacetImpl playerAssignedBuyableEntityFacet;

    /**
     * Development stage of the {@link com.nigames.jbdd.domain.entities.item.BuildingEntity} instance.
     */
    private int stage;

    /**
     * Setup and link facet instances
     * @param instance instance to be initialized with facets
     */
    protected static void initInstance(final PlayerAssignedBuildingEntity instance) {
        instance.playerAssignedEntityFacet = new PlayerAssignedEntityFacetImpl(instance);
        instance.playerAssignedBuyableEntityFacet = new PlayerAssignedBuyableEntityFacetImpl(instance);
    }

    /**
     * @return Get {@link PlayerAssignedBuildingEntity#stage}
     */
    public int getStage() {
        return stage;
    }

    /**
     * @param stage The {@link PlayerAssignedBuildingEntity#stage} to setLang
     */
    public void setStage(final int stage) {
        this.stage = stage;
    }

    @Override
    public PlayerEntity getPlayer() {
        return playerAssignedEntityFacet.getPlayer();
    }

    @Override
    public void setPlayer(PlayerEntity player) {
        playerAssignedEntityFacet.setPlayer(player);
    }

    @Override
    public long getRemainingBuildtime() {
        return playerAssignedBuyableEntityFacet.getRemainingBuildtime();
    }

    @Override
    public void setRemainingBuildtime(long remainingBuildtime) {
        playerAssignedBuyableEntityFacet.setRemainingBuildtime(remainingBuildtime);
    }

    @Override
    public void setId(final Long id) {

    }
}
