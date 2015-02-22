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
public final class PlayerAssignedEntityFacetImpl implements PlayerAssignedEntityFacet {

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

}
