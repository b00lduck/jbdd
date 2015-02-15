package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.Identifiable;
import com.nigames.jbdd.rest.dto.facet.IdentifiableImpl;
import com.nigames.jbdd.rest.dto.facet.IsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class PlayerPeople implements IsDto, Identifiable {

	private final Identifiable isIdentifiable = new IdentifiableImpl();

	@Override
	public long getId() {
		return isIdentifiable.getId();
	}

	@Override
	public void setId(final long id) {
		isIdentifiable.setId(id);
	}

}
