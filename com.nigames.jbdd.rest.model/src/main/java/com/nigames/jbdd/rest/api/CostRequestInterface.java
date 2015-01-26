package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.statics.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/cost")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CostRequestInterface extends GenericRequestInterface<Cost> {

	@Override
	@GET
	@Path("/{id}")
	Cost getById(@PathParam("id") final long id);

	@PUT
	@Path("/{id}")
	Cost update(@PathParam("id") final long id, final Cost dto);

	@POST
	@Path("/")
	Cost create(final Cost dto);

	@GET
	@Path("/buyable/{id}")
	DtoList<Cost> getCostsForBuyable(@PathParam("id") final long id,
	                                 @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                 @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                 @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                 @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/buyable/{id}/addable")
	DtoList<Good> getAddable(@PathParam("id") final long id,
	                         @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                         @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                         @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                         @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

}
