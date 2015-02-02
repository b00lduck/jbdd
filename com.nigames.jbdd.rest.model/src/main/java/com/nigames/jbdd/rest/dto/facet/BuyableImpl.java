package com.nigames.jbdd.rest.dto.facet;

public class BuyableImpl implements Buyable {

	private int buildtime;

	@Override
	public final int getBuildtime() {
		return buildtime;
	}

	@Override
	public final void setBuildtime(final int buildtime) {
		this.buildtime = buildtime;
	}

	@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if ((null == o) || (getClass() != o.getClass())) {
			return false;
		}

		final BuyableImpl buyable = (BuyableImpl) o;

		return buildtime == buyable.buildtime;

	}

	@Override
	public int hashCode() {
		return buildtime;
	}

}
