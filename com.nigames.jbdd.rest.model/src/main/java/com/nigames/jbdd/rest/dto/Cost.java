package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.IsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class Cost implements IsDto {

	private long goodId;

	private Good good;

	private long buyableId;

	private long amount;

	public long getGoodId() {
		return goodId;
	}

	public void setGoodId(final long goodId) {
		this.goodId = goodId;
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

	public Good getGood() {
		return good;
	}

	public void setGood(final Good good) {
		this.good = good;
	}

	@Override
	public final boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Cost)) return false;

		Cost cost = (Cost) o;

		if (amount != cost.amount) return false;
		if (buyableId != cost.buyableId) return false;
		if (goodId != cost.goodId) return false;
		if (good != null ? !good.equals(cost.good) : cost.good != null) return false;

		return true;
	}

	@Override
	public final int hashCode() {
		int result = (int) (goodId ^ (goodId >>> 32));
		result = 31 * result + (good != null ? good.hashCode() : 0);
		result = 31 * result + (int) (buyableId ^ (buyableId >>> 32));
		result = 31 * result + (int) (amount ^ (amount >>> 32));
		return result;
	}

}
