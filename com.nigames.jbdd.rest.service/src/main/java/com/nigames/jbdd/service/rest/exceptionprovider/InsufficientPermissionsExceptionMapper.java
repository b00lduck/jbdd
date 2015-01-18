package com.nigames.jbdd.service.rest.exceptionprovider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
class InsufficientPermissionsExceptionMapper implements ExceptionMapper<InsufficientPermissionsException> {

	@Override
	public Response toResponse(final InsufficientPermissionsException exception) {
		return Response.status(Response.Status.FORBIDDEN).build();
		// TODO: use error object to clarify the error code
	}

}
