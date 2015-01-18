package com.nigames.jbdd.rest.dto.aspects;

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

}
