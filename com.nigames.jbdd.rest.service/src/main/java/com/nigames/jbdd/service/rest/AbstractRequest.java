package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.GenericRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Meta;
import com.nigames.jbdd.rest.dto.aspects.IsDto;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import com.nigames.jbdd.statics.Constants;

import javax.ws.rs.*;

public abstract class AbstractRequest<DtoType extends IsDto> implements
        GenericRequestInterface<DtoType> {

    /**
     * Create the {@link Meta} DTO for a {@link DtoList}.
     *
     * @param total Total number of items
     * @param size  Page size
     * @param first Start at entry
     * @return Meta object with the given values
     */
    @SuppressWarnings("ReuseOfLocalVariable")
    static Meta createMeta(final Long total, final Long first, final Long size) {

        Long fixedSize = size;
        Long fixedFirst = first;

        final Meta ret = new Meta();

        if ((null == fixedSize) || (null == fixedFirst)) {

            if (null == fixedSize) {
                fixedSize = 0L;
            }

            if (null == fixedFirst) {
                fixedFirst = 0L;
            }

            ret.setSize(fixedSize);
        } else {
            final Long numResults = total - fixedFirst;
            if (numResults < fixedSize) {
                fixedSize = numResults;
            }
            if (0L > fixedSize) {
                fixedSize = 0L;
            }
            ret.setSize(fixedSize);
        }
        ret.setTotalItems(total);
        ret.setFirst(fixedFirst);

        return ret;

    }

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
                                   @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size, @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                                   @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {

        final DtoList<DtoType> ret = new DtoList<>();

        ret.setData(getService().findAll(LimitParams.create(first, size), SortParams.create(sort, desc)));

        final Long total = getService().getCount();

        ret.setMeta(AbstractRequest.createMeta(total, first, size));

        return ret;
    }

    protected abstract AbstractDtoServiceInterface<DtoType, ?> getService();

}
