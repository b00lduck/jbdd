package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.*;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class User implements IsDto, Identifiable, CanBeEnabled, Deletable {

	private final Identifiable isIdentifiable = new IdentifiableImpl();
	private final CanBeEnabled canBeEnabled = new CanBeEnabledImpl();
	private final Deletable deletable = new DeletableImpl();

	private String username;
	private String password;
	private String email;
	private List<UserRole> roles;
	private Integer numPlayers;

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public Collection<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(final List<UserRole> roles) {
		this.roles = roles;
	}

	public Integer getNumPlayers() {
		return numPlayers;
	}

	public void setNumPlayers(final Integer numPlayers) {
		this.numPlayers = numPlayers;
	}

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

}
