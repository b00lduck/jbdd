package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.PlayerEntity;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by andersg on 09.02.2015.
 */
@Embeddable
public class PlayerAssignedEntityFacetImpl implements PlayerAssignedEntityFacet {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "player", referencedColumnName = "id", updatable = false, insertable = false)
    private PlayerEntity player;

    @Override
    public PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public void setPlayer(final PlayerEntity player) {
        this.player = player;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlayerAssignedEntityFacetImpl)) return false;

		PlayerAssignedEntityFacetImpl that = (PlayerAssignedEntityFacetImpl) o;

		if (!player.equals(that.player)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return player.hashCode();
	}
}
