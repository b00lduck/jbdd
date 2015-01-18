package com.nigames.jbdd.service.rest.exceptionprovider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
class ContentNotFoundExceptionMapper implements ExceptionMapper<ContentNotFoundException> {

    @Override
    public Response toResponse(final ContentNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
