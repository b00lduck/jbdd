package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Database entity for Players {@link com.nigames.jbdd.domain.entities.item.BuildingEntity} objects. This
 * represents the buildings a game character owns.
 *
 * @author Daniel
 */
@SuppressWarnings("ClassTooDeepInInheritanceTree")
@Entity
@Table(name = "player_building")
@NamedQueries({
        @NamedQuery(name = "findAllPlayerBuildings", query = "FROM PlayerAssignedBuildingEntity"),
        @NamedQuery(name = "findPlayerBuildingsByPlayer", query = "FROM PlayerAssignedBuildingEntity WHERE player=:player")})
public class PlayerAssignedBuildingEntity extends PlayerAssignedBuyableEntity {

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

    // TODO: hashCode, equals and toString

}
