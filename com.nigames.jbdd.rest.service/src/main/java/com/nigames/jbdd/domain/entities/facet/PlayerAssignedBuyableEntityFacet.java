package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.PlayerEntity;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by andersg on 09.02.2015.
 */
public interface PlayerAssignedBuyableEntityFacet {

    long getRemainingBuildtime();

    void setRemainingBuildtime(final long remainingBuildtime);
}
