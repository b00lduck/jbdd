package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.aspects.Identifiable;
import com.nigames.jbdd.rest.dto.aspects.IdentifiableImpl;
import com.nigames.jbdd.rest.dto.aspects.IsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class UserRole implements IsDto, Identifiable {

	/**
	 * System role. A user may not have this role at all.
	 */
	public static final String ROLE_SYSTEM = "ROLE_SYSTEM";
	public static final String ROLE_ADMIN_USER = "ROLE_ADMIN_USER";
	public static final String ROLE_ADMIN_PLAYER = "ROLE_ADMIN_PLAYER";
	public static final String ROLE_PLAYER = "ROLE_PLAYER";
	private final Identifiable isIdentifiable = new IdentifiableImpl();
	private long id;

	private String roleName;

	public UserRole() {
	}

	@SuppressWarnings("CallToSimpleSetterFromWithinClass")
	public UserRole(final String roleName) {
		if (ROLE_SYSTEM.equals(roleName)) {
			// TODO: do proper error handling
			return;
		}
		setRoleName(roleName);
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(final String roleName) {
		this.roleName = roleName;
	}

	@Override
	public long getId() {
		return isIdentifiable.getId();
	}

	@Override
	public void setId(final long id) {
		isIdentifiable.setId(id);
	}

}
