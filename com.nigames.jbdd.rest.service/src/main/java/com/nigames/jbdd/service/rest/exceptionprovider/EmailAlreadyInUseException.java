package com.nigames.jbdd.service.rest.exceptionprovider;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 11.01.2015.
 */
public class EmailAlreadyInUseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final String email;

	public EmailAlreadyInUseException(String email) {
		this.email = email;
	}

}
