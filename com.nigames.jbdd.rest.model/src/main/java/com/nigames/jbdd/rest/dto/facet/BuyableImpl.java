package com.nigames.jbdd.rest.dto.facet;

public final class BuyableImpl implements Buyable {

	private int buildtime;

	@Override
	public int getBuildtime() {
		return buildtime;
	}

	@Override
	public void setBuildtime(final int buildtime) {
		this.buildtime = buildtime;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof BuyableImpl)) return false;

		BuyableImpl buyable = (BuyableImpl) o;

		if (buildtime != buyable.buildtime) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return buildtime;
	}
}
