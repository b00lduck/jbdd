package com.nigames.jbdd.service.rest.exceptionprovider;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 11.01.2015.
 */
public class UsernameAlreadyInUseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String username;

	public UsernameAlreadyInUseException(final String username) {
		this.username = username;
	}

}
