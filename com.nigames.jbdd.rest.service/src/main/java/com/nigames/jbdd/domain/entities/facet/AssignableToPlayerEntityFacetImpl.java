package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.PlayerEntity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by zerlettd on 19.12.2014.
 */
@Embeddable
public class AssignableToPlayerEntityFacetImpl implements AssignableToPlayerEntityFacet {

    /**
     * The owning {@link com.nigames.jbdd.domain.entities.PlayerEntity}.
     */
    @NotNull
    @ManyToOne
    private PlayerEntity player;

    @Override
    public final PlayerEntity getPlayer() {
        return player;
    }

    @Override
    public final void setPlayer(final PlayerEntity player) {
        this.player = player;
    }

}
