package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.StoragetypeRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Storagetype;
import com.nigames.jbdd.service.service.item.StoragetypeService;
import com.nigames.jbdd.statics.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@SuppressWarnings("ALL")
@Component
@Path("/storagetype")
public class StoragetypeRequest extends AbstractRequest<Storagetype> implements
        StoragetypeRequestInterface {

    @Autowired
    private transient StoragetypeService storagetypeService;

    @Override
    protected StoragetypeService getService() {
        return storagetypeService;
    }

    @Override
    @GET
    @Path("/")
    public DtoList<Storagetype> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
                                       @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size, @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                                       @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
        return super.getAll(first, size, sort, desc);
    }

    @Override
    @GET
    @Path("/{id}")
    public Storagetype getById(@PathParam("id") final long id) {
        return getService().findById(id);
    }

    @Override
    @PUT
    @Path("/{id}")
    public Storagetype update(@PathParam("id") final long id, final Storagetype dto) {
        return getService().update(id, dto);
    }

    @Override
    @POST
    @Path("/")
    public Storagetype create(final Storagetype dto) {
        return getService().create(dto);
    }

}
