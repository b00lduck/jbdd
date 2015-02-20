package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedEntityFacet;
import com.nigames.jbdd.domain.entities.facet.PlayerAssignedEntityFacetImpl;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Abstract Database entity for Players People. This represents the people a a game character owns.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player_assigned_people")
public class PlayerAssignedPeopleEntity extends IdentifyableEntityFacetImpl implements PlayerAssignedEntityFacet {

    /**
     * The {@link PlayerAssignedBuildingEntity} in which the People work.
     */
    @ManyToOne
    private PlayerAssignedBuildingEntity playerBuilding;

	@Embedded
	private PlayerAssignedEntityFacetImpl playerAssignedEntityFacet;

    /**
     * The workmode.
     */
    private PeopleWorkmode workmode;

    /**
     * The name of the inhabitant.
     */
    private String name;

    /**
     * @return Get {@link PlayerAssignedPeopleEntity#playerBuilding}
     */
    public PlayerAssignedBuildingEntity getPlayerBuilding() {
        return playerBuilding;
    }

    /**
     * @param playerBuilding The {@link PlayerAssignedPeopleEntity#playerBuilding} to setLang
     */
    public void setPlayerBuilding(final PlayerAssignedBuildingEntity playerBuilding) {
        this.playerBuilding = playerBuilding;
    }

    /**
     * @return Get {@link PlayerAssignedPeopleEntity#workmode}
     */
    public PeopleWorkmode getWorkmode() {
        return workmode;
    }

    /**
     * @param workmode The {@link PlayerAssignedPeopleEntity#workmode} to setLang
     */
    public void setWorkmode(final PeopleWorkmode workmode) {
        this.workmode = workmode;
    }

    /**
     * @return Get {@link PlayerAssignedPeopleEntity#name}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The {@link PlayerAssignedPeopleEntity#name} to setLang
     */
    public void setName(final String name) {
        this.name = name;
    }


    @Override
    public PlayerEntity getPlayer() {
        return playerAssignedEntityFacet.getPlayer();
    }

    @Override
    public void setPlayer(final PlayerEntity player) {
        playerAssignedEntityFacet.setPlayer(player);
    }

	@Override
	public String toString() {
		return "PlayerAssignedPeopleEntity{" +
				"name='" + name + "' " +
				"player='" + getPlayer().getNickname() + "}";
	}
}
