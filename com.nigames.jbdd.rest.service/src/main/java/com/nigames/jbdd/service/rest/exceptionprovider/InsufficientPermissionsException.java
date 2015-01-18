package com.nigames.jbdd.service.rest.exceptionprovider;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 21.12.2014.
 */
public class InsufficientPermissionsException extends RuntimeException {

	public InsufficientPermissionsException(final String message) {
		super(message);
	}

	public InsufficientPermissionsException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
