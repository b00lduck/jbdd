package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for {@link PlayerAssignedResourceEntity}.
 */
@Embeddable
public class PlayerAssignedResourceEntityPK implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of the {@link com.nigames.jbdd.domain.entities.PlayerEntity}.
     */
    @Column(name = "player_id")
    private long playerId;

    /**
     * The id of the {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @Column(name = "resource_id")
    private long resourceId;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(final long playerId) {
        this.playerId = playerId;
    }

    public long getResourceId() {
        return resourceId;
    }

    public void setResourceId(final long resourceId) {
        this.resourceId = resourceId;
    }

    // TODO: equals, hashCode and toString

}
