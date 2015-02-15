package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.api.aspect.BuyableRequestInterface;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.statics.Constants;
import com.wordnik.swagger.annotations.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/building")
@Api(value = "/building", description = "Operations about global building objects")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BuildingRequestInterface extends BuyableRequestInterface {

	@GET
	@Path("/")
	@ApiOperation("getAll")
	DtoList<Building> getAll(@ApiParam(value = "index of the first item to display", required = false) @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                         @ApiParam(value = "number of results to fetch", required = false) @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                         @ApiParam(value = "sort column name", required = false) @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                         @ApiParam(value = "sort descending", required = false) @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/{itemId}")
	@ApiOperation("getById")
	Building getById(@ApiParam(value = "ID of building to fetch", required = true)
	                 @PathParam("itemId")
	                 final long itemId);

	@PUT
	@Path("/{itemId}")
	@ApiOperation("update")
	Building update(@PathParam("itemId") final long itemId, final Building dto);

	@POST
	@Path("/")
	@ApiOperation("create")
	Building create(final Building dto);

	@DELETE
	@Path("/{id}")
	@ApiOperation("deleteById")
	@ApiResponses({
			@ApiResponse(code = 204, message = "The building was successfully deleted."),
			@ApiResponse(code = 404, message = "Building nonexistant, no deletitions performed.")
	})
	void deleteById(@PathParam("id") final long id);

	@Override
	@GET
	@Path("/{itemId}/cost")
	@ApiOperation("getCosts")
	DtoList<Cost> getCosts(@PathParam("itemId") final long itemId,
						   @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
						   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
						   @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
						   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@Override
	@GET
	@Path("/{itemId}/cost/addable")
	@ApiOperation("getAddableCostGoods")
	DtoList<Good> getAddableCostGoods(@PathParam("itemId") final long itemId,
	                                  @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@Override
	@POST
	@Path("/{itemId}/cost")
	@ApiOperation("createCost")
	Cost createCost(@PathParam("itemId") final long itemId, final Cost dto);

	@Override
	@DELETE
	@Path("/{itemId}/cost/{goodId}")
	@ApiOperation("deleteCost")
	void deleteCost(@PathParam("itemId") final long itemId, @PathParam("goodId") final long goodId);

	@Override
	@PUT
	@Path("/{itemId}/cost")
	@ApiOperation("updateCost")
	Cost updateCost(@PathParam("itemId") final long itemId, final Cost dto);


}
