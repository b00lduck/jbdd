package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.ResultList;

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
		Technology.class,
		User.class,
		UserRoleEnum.class
})
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class DtoList<T> {

	private final List<T> data;
	private final Meta meta;

	public DtoList() {
		this(new ArrayList<>(), new Meta());
	}

    public DtoList(final ResultList<T> data, final LimitParams limitParams) {
	    this(data, Meta.create(data.getTotalCount(), limitParams));
    }

	private DtoList(final List<T> data, final Meta meta) {
		this.data = data;
		this.meta = meta;
	}

	public List<T> getData() {
		return data;
	}

	public Meta getMeta() {
		return meta;
	}

}
