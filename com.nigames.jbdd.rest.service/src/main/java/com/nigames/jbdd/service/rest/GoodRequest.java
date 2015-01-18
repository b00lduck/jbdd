package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.GoodRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.statics.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@SuppressWarnings("ALL")
@Component
@Path("/good")
public class GoodRequest extends AbstractRequest<Good> implements GoodRequestInterface {

    @Autowired
    private transient GoodService goodService;

    @Override
    protected GoodService getService() {
        return goodService;
    }

    @Override
    @GET
    @Path("/")
    public DtoList<Good> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
                                @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size, @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                                @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
        return super.getAll(first, size, sort, desc);
    }

    @Override
    @GET
    @Path("/{id}")
    public Good getById(@PathParam("id") final long id) {
        return getService().findById(id);
    }

    @Override
    @PUT
    @Path("/{id}")
    public Good update(@PathParam("id") final long id, final Good dto) {
        return getService().update(id, dto);
    }

    @Override
    @POST
    @Path("/")
    public Good create(final Good dto) {
        return getService().create(dto);
    }

}
