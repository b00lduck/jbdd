package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.statics.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserRequestInterface extends GenericRequestInterface<User> {

	@Override
	@GET
	@Path("/{id}")
	User getById(@PathParam("id") final long id);

	@PUT
	@Path("/{id}")
	User update(@PathParam("id") final long id,
	            @QueryParam("sendpw") final boolean sendPassword, final User dto);

	@POST
	@Path("/")
	User create(@QueryParam("sendpw") final boolean sendPassword, final User dto);

	@GET
	@Path("/{id}/player")
	DtoList<Player> getAllPlayers(@PathParam("id") final long id,
	                              @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first, @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                              @QueryParam(Constants.QUERY_PARAM_SORT) final String sort, @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@DELETE
	@Path("/{uid}/player/{pid}")
	void removePlayer(@PathParam("uid") final long userId,
					  @PathParam("pid") final long playerId);

	@PUT
	@Path("/{uid}/player/{pid}")
	void addPlayer(@PathParam("uid") final long userId,
				   @PathParam("pid") final long playerId);

}
