package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.statics.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/player")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PlayerRequestInterface {

	@GET
	@Path("/{id}")
	Player getById(@PathParam("id") final long id);

	@PUT
	@Path("/{id}")
	Player update(@PathParam("id") final long id, final Player dto);

	@POST
	@Path("/")
	Player create(final Player dto);

	@GET
	@Path("/unused")
	DtoList<Player> getAllUnused(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                             @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size, @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                             @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/")
	DtoList<Player> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                       @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size, @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                       @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@DELETE
	@Path("/{id}")
	void deleteById(@PathParam("id") final long id);

}
