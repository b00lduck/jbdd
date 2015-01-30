package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.types.LimitParams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({User.class, UserRole.class, Player.class, Good.class, Job.class, Storagetype.class})
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtoList<T> {

	public DtoList(final List<T> data, final long total, final LimitParams limitParams) {
		setData(data);
		setMeta(Meta.create(total, limitParams));
	}

	private List<T> data = new ArrayList<>();

	private Meta meta = new Meta();

	public List<T> getData() {
		return data;
	}

	private void setData(final List<T> data) {
		this.data = data;
	}

	public Meta getMeta() {
		return meta;
	}

	private void setMeta(final Meta meta) {
		this.meta = meta;
	}

}
