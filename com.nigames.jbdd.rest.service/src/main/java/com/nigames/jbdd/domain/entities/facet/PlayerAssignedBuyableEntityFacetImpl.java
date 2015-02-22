package com.nigames.jbdd.domain.entities.facet;

import javax.persistence.Embeddable;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by andersg on 09.02.2015.
 */
@Embeddable
public final class PlayerAssignedBuyableEntityFacetImpl implements PlayerAssignedBuyableEntityFacet {

    private long remainingBuildtime;

    @Override
    public long getRemainingBuildtime() {
        return remainingBuildtime;
    }

    @Override
    public void setRemainingBuildtime(final long remainingBuildtime) {
        this.remainingBuildtime = remainingBuildtime;
    }

}
