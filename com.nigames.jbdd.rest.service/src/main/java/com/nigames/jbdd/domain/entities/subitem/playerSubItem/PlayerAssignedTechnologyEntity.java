package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Database entity for Players {@link com.nigames.jbdd.domain.entities.item.TechnologyEntity} objects.
 * This represents the technologies a game character owns.
 *
 * @author Daniel
 */
@SuppressWarnings("ClassTooDeepInInheritanceTree")
@Entity
@Table(name = "player_technology")
@NamedQueries({
        @NamedQuery(name = "findAllPlayerTechnologies", query = "FROM PlayerAssignedTechnologyEntity"),
        @NamedQuery(name = "findPlayerTechnologiesByPlayer",
                query = "FROM PlayerAssignedTechnologyEntity WHERE player=:player")})
public class PlayerAssignedTechnologyEntity extends PlayerAssignedBuyableEntity {

    /**
     * Development stage of the {@link com.nigames.jbdd.domain.entities.item.BuildingEntity} instance.
     */
    private Integer stage;

    /**
     * @return Get {@link PlayerAssignedBuildingEntity#stage}
     */
    public Integer getStage() {
        return stage;
    }

    /**
     * @param stage The {@link PlayerAssignedBuildingEntity#stage} to setLang
     */
    public void setStage(final Integer stage) {
        this.stage = stage;
    }

    // TODO: hashCode, equals and toString

}

