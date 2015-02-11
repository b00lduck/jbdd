package com.nigames.jbdd.domain.entities.facet;

import javax.persistence.Embeddable;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by andersg on 09.02.2015.
 */
@Embeddable
public class PlayerAssignedBuyableEntityFacetImpl implements PlayerAssignedBuyableEntityFacet {

    private long remainingBuildtime;

    @Override
    public long getRemainingBuildtime() {
        return remainingBuildtime;
    }

    @Override
    public void setRemainingBuildtime(final long remainingBuildtime) {
        this.remainingBuildtime = remainingBuildtime;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof PlayerAssignedBuyableEntityFacetImpl)) return false;

		PlayerAssignedBuyableEntityFacetImpl that = (PlayerAssignedBuyableEntityFacetImpl) o;

		if (remainingBuildtime != that.remainingBuildtime) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (int) (remainingBuildtime ^ (remainingBuildtime >>> 32));
	}

}
