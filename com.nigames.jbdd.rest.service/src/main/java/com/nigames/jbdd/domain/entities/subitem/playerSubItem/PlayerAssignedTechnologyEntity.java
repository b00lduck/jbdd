package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedBuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedBuyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedEntityFacet;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedEntityFacetImpl;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.item.BuildingEntity;

import javax.persistence.*;

/**
 * Database entity for Players {@link com.nigames.jbdd.domain.entities.item.TechnologyEntity} objects. This
 * represents the technologys a game character owns.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player_assigned_technology")
public class PlayerAssignedTechnologyEntity extends IdentifyableEntityFacetImpl implements PlayerAssignedEntityFacet,
		PlayerAssignedBuyableEntityFacet {

	@Embedded
	private PlayerAssignedEntityFacetImpl playerAssignedEntityFacet;

	@Embedded
	private PlayerAssignedBuyableEntityFacetImpl playerAssignedBuyableEntityFacet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id")
	private BuildingEntity building;

    /**
     * Development stage of the {@link com.nigames.jbdd.domain.entities.item.TechnologyEntity} instance.
     */
    private int stage;

    /**
     * @return Get {@link PlayerAssignedTechnologyEntity#stage}
     */
    public int getStage() {
        return stage;
    }

    /**
     * @param stage The {@link PlayerAssignedTechnologyEntity#stage} to setLang
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

}
