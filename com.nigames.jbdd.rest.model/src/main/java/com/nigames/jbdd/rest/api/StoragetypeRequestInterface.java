package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.Storagetype;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/storagetype")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface StoragetypeRequestInterface extends GenericRequestInterface<Storagetype> {

	@PUT
	@Path("/{id}")
	Storagetype update(@PathParam("id") final long id, final Storagetype dto);

	@POST
	@Path("/")
	Storagetype create(final Storagetype dto);

	@Override
	@GET
	@Path("/{id}")
	Storagetype getById(@PathParam("id") final long id);

}
