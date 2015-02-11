package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedPeopleEntity;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by andersg on 09.02.2015.
 */
public interface PlayerAssignedEntityFacet {

    PlayerEntity getPlayer();

    void setPlayer(final PlayerEntity player);
}
