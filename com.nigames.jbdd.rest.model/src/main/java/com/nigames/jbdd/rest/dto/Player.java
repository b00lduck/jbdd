package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class Player implements IsDto, Identifiable, CanBeEnabled, Deletable {

	private final Identifiable isIdentifiable = new IdentifiableImpl();
	private final CanBeEnabled canBeEnabled = new CanBeEnabledImpl();
	private final Deletable deletable = new DeletableImpl();

	private String nickname;

	@Override
	public boolean isEnabled() {
		return canBeEnabled.isEnabled();
	}

	@Override
	public void setEnabled(final boolean enabled) {
		canBeEnabled.setEnabled(enabled);
	}

	@Override
	public long getId() {
		return isIdentifiable.getId();
	}

	@Override
	public void setId(final long id) {
		isIdentifiable.setId(id);
	}

	@Override
	public boolean isDeletable() {
		return deletable.isDeletable();
	}

	@Override
	public void setDeletable(boolean deletable) {
		this.deletable.setDeletable(deletable);
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(final String nickname) {
		this.nickname = nickname;
	}

}
