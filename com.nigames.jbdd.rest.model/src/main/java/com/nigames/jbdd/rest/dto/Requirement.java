package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.rest.dto.facet.IsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class Requirement implements IsDto {

	private long requiredBuyableId;

	private Buyable requiredBuyable;

	private long buyableId;

	private long amount;

	public long getRequiredBuyableId() {
		return requiredBuyableId;
	}

	public void setRequiredBuyableId(final long requiredBuyableId) {
		this.requiredBuyableId = requiredBuyableId;
	}

	public Buyable getRequiredBuyable() {
		return requiredBuyable;
	}

	public void setRequiredBuyable(final Buyable requiredBuyable) {
		this.requiredBuyable = requiredBuyable;
	}

	public long getBuyableId() {
		return buyableId;
	}

	public void setBuyableId(final long buyableId) {
		this.buyableId = buyableId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(final long amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Requirement)) {
			return false;
		}

		final Requirement that = (Requirement) o;

		if (amount != that.amount) {
			return false;
		}
		if (buyableId != that.buyableId) {
			return false;
		}
		if (requiredBuyableId != that.requiredBuyableId) {
			return false;
		}
		return !((null != requiredBuyable) ? !requiredBuyable.equals(that.requiredBuyable) : (null != that.requiredBuyable));

	}

	@Override
	public int hashCode() {
		int result = (int) (requiredBuyableId ^ (requiredBuyableId >>> 32));
		result = (31 * result) + ((null != requiredBuyable) ? requiredBuyable.hashCode() : 0);
		result = (31 * result) + (int) (buyableId ^ (buyableId >>> 32));
		result = (31 * result) + (int) (amount ^ (amount >>> 32));
		return result;
	}

}
