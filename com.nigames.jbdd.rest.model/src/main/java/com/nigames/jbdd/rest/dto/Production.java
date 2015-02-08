package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.IsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Production implements IsDto {

	private long goodId;

	private Good good;

	private long buildingId;

	private long amount;

	public long getGoodId() {
		return goodId;
	}

	public void setGoodId(final long goodId) {
		this.goodId = goodId;
	}

	public long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(final long buildingId) {
		this.buildingId = buildingId;
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

}
