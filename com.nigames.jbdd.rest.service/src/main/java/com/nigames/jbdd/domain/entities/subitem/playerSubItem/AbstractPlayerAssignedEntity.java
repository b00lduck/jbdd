/**
 *
 */
package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.aspect.AssignableToPlayerEntityAspect;
import com.nigames.jbdd.domain.entities.aspect.AssignableToPlayerEntityAspectImpl;
import com.nigames.jbdd.domain.entities.aspect.identifyable.IdentifyableEntityAspectImpl;

import javax.persistence.Embedded;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class AbstractPlayerAssignedEntity extends IdentifyableEntityAspectImpl implements AssignableToPlayerEntityAspect {

    @Embedded
    private AssignableToPlayerEntityAspectImpl player;

    @Override
    public final PlayerEntity getPlayer() {
        return player.getPlayer();
    }

    @Override
    public final void setPlayer(final PlayerEntity player) {
        this.player.setPlayer(player);
    }
}
