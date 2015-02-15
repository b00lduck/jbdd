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
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof AuthenticationInfo)) {
			return false;
		}

		final AuthenticationInfo that = (AuthenticationInfo) o;

		if ((null != roles) ? !roles.equals(that.roles) : (null != that.roles)) {
			return false;
		}
		return !((null != username) ? !username.equals(that.username) : (null != that.username));

	}

	@Override
	public int hashCode() {
		int result = (null != username) ? username.hashCode() : 0;
		result = (31 * result) + ((null != roles) ? roles.hashCode() : 0);
		return result;
	}

}
