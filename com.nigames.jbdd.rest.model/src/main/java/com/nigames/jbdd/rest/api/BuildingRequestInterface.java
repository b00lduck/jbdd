package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.statics.Constants;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@SuppressWarnings("AbstractMethodOverridesAbstractMethod")
@Path("/building")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BuildingRequestInterface extends GenericRequestInterface<Building> {

	@Override
	@GET
	@Path("/{buildingId}")
	Building getById(@PathParam("buildingId") final long buildingId);

	@PUT
	@Path("/{buildingId}")
	Building update(@PathParam("buildingId") final long buildingId, final Building dto);

	@POST
	@Path("/")
	Building create(final Building dto);

	@GET
	@Path("/{buildingId}/cost")
	DtoList<Cost> getCosts(@PathParam("buildingId") final long buildingId,
						   @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
						   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
						   @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
						   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@GET
	@Path("/{buildingId}/cost/addable")
	DtoList<Good> getAddableCosts(@PathParam("buildingId") final long buildingId,
							      @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
							      @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
							      @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
							      @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@POST
	@Path("/{buildingId}/cost")
	Cost createCost(@PathParam("buildingId") final long buildingId, final Cost dto);

	@DELETE
	@Path("/{buildingId}/cost/{goodId}")
	Cost deleteCost(@PathParam("buildingId") final long buildingId, @PathParam("goodId") final long goodId);

	@PUT
	@Path("/{buildingId}")
	Cost updateCost(@PathParam("buildingId") final long buildingId, final Cost dto);

}
