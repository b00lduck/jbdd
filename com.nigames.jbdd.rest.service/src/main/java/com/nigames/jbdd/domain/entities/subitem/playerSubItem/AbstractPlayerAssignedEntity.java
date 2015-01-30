/**
 *
 */
package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.facet.AssignableToPlayerEntityFacet;
import com.nigames.jbdd.domain.entities.facet.AssignableToPlayerEntityFacetImpl;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class AbstractPlayerAssignedEntity extends IdentifyableEntityFacetImpl implements AssignableToPlayerEntityFacet {

    @Embedded
    private AssignableToPlayerEntityFacetImpl player;

    @Override
    public final PlayerEntity getPlayer() {
        return player.getPlayer();
    }

    @Override
    public final void setPlayer(final PlayerEntity player) {
        this.player.setPlayer(player);
    }
}
