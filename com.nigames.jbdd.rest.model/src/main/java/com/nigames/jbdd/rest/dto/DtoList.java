package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.types.LimitParams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({
		Building.class,
		Cost.class,
		Good.class,
		Job.class,
		Player.class,
		PlayerBuilding.class,
		PlayerPeople.class,
		PlayerResource.class,
		PlayerTechnology.class,
		Requirement.class,
		Storagetype.class,
		Technology.class,
		User.class,
		UserRoleEnum.class
})
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class DtoList<T> {

	private List<T> data = new ArrayList<>();
	private Meta meta = new Meta();

	public DtoList() {
	}

	public DtoList(final List<T> data, final Long total, final LimitParams limitParams) {
		setData(data);
		setMeta(Meta.create(total, limitParams));
	}

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
