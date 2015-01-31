package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.facet.AssignableToPlayerEntityFacet;
import com.nigames.jbdd.domain.entities.item.GoodEntity;

import javax.persistence.*;

/**
 * Database entity for Players. This represents a game character.
 *
 * @author Daniel
 */
@Entity
@Table(name = "player_resource")
@NamedQueries({
        @NamedQuery(name = "findAllPlayerAssignedResources", query = "FROM PlayerAssignedResourceEntity"),
        @NamedQuery(name = "findPlayerAssignedResourcesByPlayer", query = "FROM PlayerAssignedResourceEntity WHERE player=:player")})
public class PlayerAssignedResourceEntity implements AssignableToPlayerEntityFacet {

    @EmbeddedId
    private PlayerAssignedResourceEntityPK id = new PlayerAssignedResourceEntityPK();

    /**
     * The stored {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @MapsId("resourceId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resource_id", referencedColumnName = "id")
    private GoodEntity resource;

    /**
     * The owning {@link PlayerEntity}.
     */
    @MapsId("playerId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private PlayerEntity player;

    /**
     * The amount.
     */
    private Long amount;


    public PlayerAssignedResourceEntityPK getId() {
        return id;
    }

    public void setId(final PlayerAssignedResourceEntityPK id) {
        this.id = id;
    }


    public GoodEntity getResource() {
        return resource;
    }

    public void setResource(final GoodEntity resource) {
        this.resource = resource;
    }


    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(final PlayerEntity player) {
        this.player = player;
    }


    public Long getAmount() {
        return amount;
    }

    public void setAmount(final Long amount) {
        this.amount = amount;
    }

}
