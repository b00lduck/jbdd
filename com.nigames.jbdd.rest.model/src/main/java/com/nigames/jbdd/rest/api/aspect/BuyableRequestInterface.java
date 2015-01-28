package com.nigames.jbdd.rest.api.aspect;

import com.nigames.jbdd.rest.api.GenericRequestInterface;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.statics.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

public interface BuyableRequestInterface {

	@GET
	@Path("/{itemId}/cost")
	DtoList<Cost> getCosts(@PathParam("itemId") final long itemId,
						   @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
						   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
						   @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
						   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/{itemId}/cost/addable")
	DtoList<Good> getAddableCosts(@PathParam("itemId") final long itemId,
								  @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
								  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
								  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
								  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@POST
	@Path("/{itemId}/cost")
	Cost createCost(@PathParam("itemId") final long itemId, final Cost dto);

	@DELETE
	@Path("/{itemId}/cost/{goodId}")
	Cost deleteCost(@PathParam("itemId") final long itemId, @PathParam("goodId") final long goodId);

	@PUT
	@Path("/{itemId}")
	Cost updateCost(@PathParam("itemId") final long itemId, final Cost dto);

}
