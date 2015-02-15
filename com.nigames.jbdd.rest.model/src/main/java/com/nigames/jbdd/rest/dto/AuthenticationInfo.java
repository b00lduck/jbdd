package com.nigames.jbdd.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class AuthenticationInfo {

	private String username;

	private List<String> roles;

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public List<String> getRoles() { return roles; }

	public void setRoles(final List<String> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AuthenticationInfo)) return false;

		AuthenticationInfo that = (AuthenticationInfo) o;

		if (roles != null ? !roles.equals(that.roles) : that.roles != null) return false;
		if (username != null ? !username.equals(that.username) : that.username != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = username != null ? username.hashCode() : 0;
		result = 31 * result + (roles != null ? roles.hashCode() : 0);
		return result;
	}

}
