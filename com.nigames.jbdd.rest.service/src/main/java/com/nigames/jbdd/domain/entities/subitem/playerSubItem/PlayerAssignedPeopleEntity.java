package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.*;

/**
 * Abstract Database entity for Players People. This represents the people a a game character owns.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player_people")
@NamedQueries({
        @NamedQuery(name = "findAllPlayerPeople", query = "FROM PlayerAssignedPeopleEntity"),
        @NamedQuery(name = "findPlayerPeopleByPlayer",
                query = "FROM PlayerAssignedPeopleEntity WHERE player=:player")})
public class PlayerAssignedPeopleEntity extends AbstractPlayerAssignedEntity {

    /**
     * The {@link PlayerAssignedBuildingEntity} in which the People work.
     */
    @ManyToOne
    private PlayerAssignedBuildingEntity playerBuilding;

    /**
     * The workmode.
     */
    private PlayerAssignedPeopleEntity.Workmode workmode;

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
    public PlayerAssignedPeopleEntity.Workmode getWorkmode() {
        return workmode;
    }

    /**
     * @param workmode The {@link PlayerAssignedPeopleEntity#workmode} to setLang
     */
    public void setWorkmode(final PlayerAssignedPeopleEntity.Workmode workmode) {
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

    /**
     * Peoples workmode enum.
     */
    // TODO: export to external class
    public enum Workmode {
        /**
         * People is producing goods in the building.
         */
        PRODUCING,

        /**
         * People is building the Building.
         */
        BUILDING
    }

}
