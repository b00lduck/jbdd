package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.api.aspect.BuyableRequestInterface;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.statics.Constants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Api("/buliding")
@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/building")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BuildingRequestInterface extends GenericRequestInterface<Building>, BuyableRequestInterface {

	@Override
	@GET
	@Path("/{itemId}")
	@ApiOperation(value = "hallo", httpMethod = "GET")
	Building getById(@PathParam("itemId") final long itemId);

	@PUT
	@Path("/{itemId}")
	Building update(@PathParam("itemId") final long itemId, final Building dto);

	@POST
	@Path("/")
	Building create(final Building dto);

	@Override
	@GET
	@Path("/{itemId}/cost")
	DtoList<Cost> getCosts(@PathParam("itemId") final long itemId,
						   @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
						   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
						   @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
						   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@Override
	@GET
	@Path("/{itemId}/cost/addable")
	DtoList<Good> getAddableCostGoods(@PathParam("itemId") final long itemId,
	                                  @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@Override
	@POST
	@Path("/{itemId}/cost")
	Cost createCost(@PathParam("itemId") final long itemId, final Cost dto);

	@Override
	@DELETE
	@Path("/{itemId}/cost/{goodId}")
	void deleteCost(@PathParam("itemId") final long itemId, @PathParam("goodId") final long goodId);

	@Override
	@PUT
	@Path("/{itemId}/cost")
	Cost updateCost(@PathParam("itemId") final long itemId, final Cost dto);

}
