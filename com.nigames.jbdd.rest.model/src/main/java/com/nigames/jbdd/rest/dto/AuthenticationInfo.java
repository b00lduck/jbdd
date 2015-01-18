package com.nigames.jbdd.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class AuthenticationInfo {

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
}
