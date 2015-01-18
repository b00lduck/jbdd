package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.AuthenticationInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/authentication")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface AuthenticationRequestInterface {

	@GET
	@Path("/")
	AuthenticationInfo getAuthStatus();

	@DELETE
	@Path("/")
	void logout();

}
