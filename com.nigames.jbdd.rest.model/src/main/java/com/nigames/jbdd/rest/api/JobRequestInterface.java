package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.Job;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/job")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.APPLICATION_JSON)
public interface JobRequestInterface extends GenericRequestInterface<Job> {

	@Override
	@GET
	@Path("/{id}")
	Job getById(@PathParam("id") final long id);

	@PUT
	@Path("/{id}")
	Job update(@PathParam("id") final long id, final Job dto);

	@POST
	@Path("/")
	Job create(final Job dto);

}
