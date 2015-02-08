package com.nigames.jbdd.rest.api;

import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.statics.Constants;
import com.wordnik.swagger.annotations.ApiOperation;

import javax.ws.rs.*;

public interface GenericRequestInterface<DtoType extends IsDto> {

	@GET
	@Path("/{id}")
	DtoType getById(@PathParam("id") final long id);

	@GET
	@Path("/")
	DtoList<DtoType> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                        @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                        @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                        @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc);

	@DELETE
	@Path("/{id}")
	@ApiOperation("deleteById")
	void deleteById(@PathParam("id") final long id);

}
