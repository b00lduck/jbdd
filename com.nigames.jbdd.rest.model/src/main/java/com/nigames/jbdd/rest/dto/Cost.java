package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.IsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Cost implements IsDto {

	private long goodId;

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

}
