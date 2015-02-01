package com.nigames.jbdd.rest.api.aspect;

import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.statics.Constants;

import javax.ws.rs.*;

public interface BuyableRequestInterface {

	// Costs

	@GET
	@Path("/{itemId}/cost")
	DtoList<Cost> getCosts(@PathParam("itemId") final long itemId,
						   @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
						   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
						   @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
						   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/{itemId}/cost/addable")
	DtoList<Good> getAddableCostGoods(@PathParam("itemId") final long itemId,
	                                  @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@POST
	@Path("/{itemId}/cost")
	Cost createCost(@PathParam("itemId") final long itemId, final Cost dto);

	@DELETE
	@Path("/{itemId}/cost/{goodId}")
	void deleteCost(@PathParam("itemId") final long itemId, @PathParam("goodId") final long goodId);

	@PUT
	@Path("/{itemId}/cost/")
	Cost updateCost(@PathParam("itemId") final long itemId, final Cost dto);


	// Requirements

	@GET
	@Path("/{itemId}/requirement")
	DtoList<Requirement> getRequirements(@PathParam("itemId") final long itemId,
	                                     @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                     @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                     @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                     @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/{itemId}/requirement/addable")
	DtoList<Buyable> getAddableRequirementBuyables(@PathParam("itemId") final long itemId,
	                                               @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                               @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                               @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                               @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@POST
	@Path("/{itemId}/requirement")
	Requirement createRequirement(@PathParam("itemId") final long itemId, final Requirement dto);

	@DELETE
	@Path("/{itemId}/requirement/{requiredBuyableId}")
	void deleteRequirement(@PathParam("itemId") final long itemId, @PathParam("requiredBuyableId") final long requiredBuyableId);

	@PUT
	@Path("/{itemId}/requirement/")
	Requirement updateRequirement(@PathParam("itemId") final long itemId, final Requirement dto);

}
