package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.Good;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/good")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface GoodRequestInterface extends GenericRequestInterface<Good> {

	@Override
	@GET
	@Path("/{id}")
	Good getById(@PathParam("id") final long id);

	@PUT
	@Path("/{id}")
	Good update(@PathParam("id") final long id, final Good dto);

	@POST
	@Path("/")
	Good create(final Good dto);

}
