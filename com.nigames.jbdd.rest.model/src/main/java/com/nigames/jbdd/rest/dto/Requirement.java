package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.rest.dto.facet.IsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Requirement implements IsDto {

	private long requiredBuyableId;

	private Buyable requiredBuyable;

	private long buyableId;

	private long amount;

	public long getRequiredBuyableId() {
		return requiredBuyableId;
	}

	public void setRequiredBuyableId(long requiredBuyableId) {
		this.requiredBuyableId = requiredBuyableId;
	}

	public Buyable getRequiredBuyable() {
		return requiredBuyable;
	}

	public void setRequiredBuyable(Buyable requiredBuyable) {
		this.requiredBuyable = requiredBuyable;
	}

	public long getBuyableId() {
		return buyableId;
	}

	public void setBuyableId(long buyableId) {
		this.buyableId = buyableId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

}
