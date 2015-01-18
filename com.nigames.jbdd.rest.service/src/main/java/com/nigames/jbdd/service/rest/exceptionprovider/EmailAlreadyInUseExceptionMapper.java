package com.nigames.jbdd.service.rest.exceptionprovider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
class EmailAlreadyInUseExceptionMapper implements ExceptionMapper<EmailAlreadyInUseException> {

	@Override
	public Response toResponse(final EmailAlreadyInUseException exception) {
		return Response.status(Response.Status.CONFLICT).build();
		// TODO: use error object to clarify the error code
	}

}
