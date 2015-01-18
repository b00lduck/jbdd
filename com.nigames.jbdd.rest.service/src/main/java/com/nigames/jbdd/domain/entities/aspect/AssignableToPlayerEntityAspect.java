package com.nigames.jbdd.domain.entities.aspect;

import com.nigames.jbdd.domain.entities.PlayerEntity;

public interface AssignableToPlayerEntityAspect {

    PlayerEntity getPlayer();

    void setPlayer(final PlayerEntity player);

}
