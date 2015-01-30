package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.PlayerEntity;

public interface AssignableToPlayerEntityFacet {

    PlayerEntity getPlayer();

    void setPlayer(PlayerEntity player);

}
