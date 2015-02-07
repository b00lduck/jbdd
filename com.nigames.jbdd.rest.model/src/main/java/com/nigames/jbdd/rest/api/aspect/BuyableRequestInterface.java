package com.nigames.jbdd.rest.api.aspect;

import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.statics.Constants;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.*;

public interface BuyableRequestInterface {

	// Costs
	@GET
	@Path("/{itemId}/cost")
	@ApiOperation("getCosts")
	DtoList<Cost> getCosts(@PathParam("itemId") final long itemId,
						   @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
						   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
						   @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
						   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/{itemId}/cost/addable")
	@ApiOperation("getAddableCostGoods")
	DtoList<Good> getAddableCostGoods(@PathParam("itemId") final long itemId,
	                                  @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@POST
	@Path("/{itemId}/cost")
	@ApiOperation("createCost")
	Cost createCost(@PathParam("itemId") final long itemId, final Cost dto);

	@DELETE
	@Path("/{itemId}/cost/{goodId}")
	@ApiOperation("deleteCost")
	void deleteCost(@PathParam("itemId") final long itemId, @PathParam("goodId") final long goodId);

	@PUT
	@Path("/{itemId}/cost/")
	@ApiOperation("updateCost")
	Cost updateCost(@PathParam("itemId") final long itemId, final Cost dto);


	// Requirements

	@GET
	@Path("/{itemId}/requirement")
	@ApiOperation("getRequirements")
	DtoList<Requirement> getRequirements(@PathParam("itemId") final long itemId,
	                                     @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                     @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                     @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                     @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/{itemId}/requirement/addable")
	@ApiOperation("getAddableRequirementBuyables")
	DtoList<Buyable> getAddableRequirementBuyables(@PathParam("itemId") final long itemId,
	                                               @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                               @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                               @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                               @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@POST
	@Path("/{itemId}/requirement")
	@ApiOperation("createRequirement")
	Requirement createRequirement(@PathParam("itemId") final long itemId, final Requirement dto);

	@DELETE
	@Path("/{itemId}/requirement/{requiredBuyableId}")
	@ApiOperation("deleteRequirement")
	void deleteRequirement(@PathParam("itemId") final long itemId, @PathParam("requiredBuyableId") final long requiredBuyableId);

	@PUT
	@Path("/{itemId}/requirement/")
	@ApiOperation("updateRequirement")
	Requirement updateRequirement(@PathParam("itemId") final long itemId, final Requirement dto);

}
