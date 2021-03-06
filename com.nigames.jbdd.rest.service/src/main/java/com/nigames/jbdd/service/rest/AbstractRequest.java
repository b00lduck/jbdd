package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.GenericRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.statics.Constants;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;

import javax.ws.rs.*;

public abstract class AbstractRequest<DtoType extends IsDto> implements
        GenericRequestInterface<DtoType> {

    @Override
    @DELETE
    @Path("/{id}")
    public void deleteById(@PathParam("id") final long id) {
        getService().delete(id);
    }

    @Override
    @GET
    @Path("/")
    public DtoList<DtoType> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
                                   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
                                   @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                                   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {

        final LimitParams limitParams = LimitParams.create(first, size);
        final SortParams sortParams = SortParams.create(sort, desc);

	    final ResultList<DtoType> data = getService().findAll(limitParams, sortParams);

        return new DtoList<>(data, limitParams);

    }

	protected abstract AbstractDtoServiceInterface<DtoType> getService();

}
