package com.nigames.jbdd.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Meta {

	private Long totalItems;

	private Long first;

	private Long size;

	public Long getTotalItems() {
		return totalItems;
	}

	public void setTotalItems(final Long totalItems) {
		this.totalItems = totalItems;
	}

	public Long getFirst() {
		return first;
	}

	public void setFirst(final Long first) {
		this.first = first;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(final Long size) {
		this.size = size;
	}
}
