package com.nigames.jbdd.service.rest.exceptionprovider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
class UsernameAlreadyInUseExceptionMapper implements ExceptionMapper<UsernameAlreadyInUseException> {

	@Override
	public Response toResponse(final UsernameAlreadyInUseException exception) {
		return Response.status(Response.Status.CONFLICT).build();
		// TODO: use error object to clarify the error code
	}

}
