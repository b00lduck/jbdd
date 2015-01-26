package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.Building;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/building")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BuildingRequestInterface extends GenericRequestInterface<Building> {

	@Override
	@GET
	@Path("/{id}")
	Building getById(@PathParam("id") final long id);

	@PUT
	@Path("/{id}")
	Building update(@PathParam("id") final long id, final Building dto);

	@POST
	@Path("/")
	Building create(final Building dto);

}
