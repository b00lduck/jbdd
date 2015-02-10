package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for {@link PlayerAssignedGoodEntity}.
 */
@Embeddable
public class PlayerAssignedGoodEntityPK implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of the {@link com.nigames.jbdd.domain.entities.PlayerEntity}.
     */
    @Column(name = "player")
    private long playerId;

    /**
     * The id of the {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @Column(name = "good")
    private long goodId;

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(final long playerId) {
        this.playerId = playerId;
    }

	public long getGoodId() {
		return goodId;
	}

	public void setGoodId(final long goodId) {
		this.goodId = goodId;
	}

    // TODO: equals, hashCode and toString

}
