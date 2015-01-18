package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.JobRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.service.service.item.JobService;
import com.nigames.jbdd.statics.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@SuppressWarnings("ALL")
@Component
@Path("/job")
public class JobRequest extends AbstractRequest<Job> implements JobRequestInterface {

    @Autowired
    private transient JobService jobService;

    @Override
    protected JobService getService() {
        return jobService;
    }

    @Override
    @GET
    @Path("/")
    public DtoList<Job> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
                               @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size, @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                               @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
        return super.getAll(first, size, sort, desc);
    }

    @Override
    @GET
    @Path("/{id}")
    public Job getById(@PathParam("id") final long id) {
        return getService().findById(id);
    }

    @Override
    @PUT
    @Path("/{id}")
    public Job update(@PathParam("id") final long id, final Job dto) {
        return getService().update(id, dto);
    }

    @Override
    @POST
    @Path("/")
    public Job create(final Job dto) {
        return getService().create(dto);
    }

}
