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
 * Database entity for Players {@link com.nigames.jbdd.domain.entities.item.BuildingEntity} objects. This
 * represents the buildings a game character owns.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player_assigned_building")
public class PlayerAssignedBuildingEntity extends IdentifyableEntityFacetImpl implements PlayerAssignedEntityFacet,
		PlayerAssignedBuyableEntityFacet {

	@Embedded
	private PlayerAssignedEntityFacetImpl playerAssignedEntityFacet;

	@Embedded
	private PlayerAssignedBuyableEntityFacetImpl playerAssignedBuyableEntityFacet;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "id")
	private BuildingEntity building;

    /**
     * Development stage of the {@link com.nigames.jbdd.domain.entities.item.BuildingEntity} instance.
     */
    private int stage;

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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlayerAssignedBuildingEntity)) return false;

		PlayerAssignedBuildingEntity that = (PlayerAssignedBuildingEntity) o;

		if (stage != that.stage) return false;
		if (!building.equals(that.building)) return false;
		if (!playerAssignedBuyableEntityFacet.equals(that.playerAssignedBuyableEntityFacet)) return false;
		if (!playerAssignedEntityFacet.equals(that.playerAssignedEntityFacet)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = playerAssignedEntityFacet.hashCode();
		result = 31 * result + playerAssignedBuyableEntityFacet.hashCode();
		result = 31 * result + building.hashCode();
		result = 31 * result + stage;
		return result;
	}

}
