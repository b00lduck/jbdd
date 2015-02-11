package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.rest.dto.Production;
import com.nigames.jbdd.statics.Constants;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/job")
@Api(value = "/job", description = "Operations about global job objects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface JobRequestInterface {

	@GET
	@Path("/{id}")
	@ApiOperation(value = "getById")
	Job getById(@PathParam("id") final long id);

	@PUT
	@Path("/{id}")
	@ApiOperation(value = "update")
	Job update(@PathParam("id") final long id, final Job dto);

	@POST
	@Path("/")
	@ApiOperation(value = "create")
	Job create(final Job dto);

	@GET
	@Path("/")
	@ApiOperation(value = "getAll")
	DtoList<Job> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                    @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                    @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                    @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@DELETE
	@Path("/{id}")
	@ApiOperation(value = "deleteById")
	void deleteById(@PathParam("id") final long id);

	// Production

	@GET
	@Path("/{itemId}/production")
	@ApiOperation("getProductions")
	DtoList<Production> getProductions(@PathParam("itemId") final long itemId,
	                                   @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                   @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/{itemId}/production/addable")
	@ApiOperation("getAddableProductionGoods")
	DtoList<Good> getAddableProductionGoods(@PathParam("itemId") final long itemId,
	                                        @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                        @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                        @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                        @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@POST
	@Path("/{itemId}/production")
	@ApiOperation("createProduction")
	Production createProduction(@PathParam("itemId") final long itemId, final Production dto);

	@DELETE
	@Path("/{itemId}/production/{goodId}")
	@ApiOperation("deleteProduction")
	void deleteProduction(@PathParam("itemId") final long itemId, @PathParam("goodId") final long goodId);

	@PUT
	@Path("/{itemId}/production/")
	@ApiOperation("updateProduction")
	Production updateProduction(@PathParam("itemId") final long itemId, final Production dto);


}
