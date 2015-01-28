package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.BuildingRequestInterface;
import com.nigames.jbdd.rest.dto.*;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import com.nigames.jbdd.service.service.subitem.buyable.CostService;
import com.nigames.jbdd.statics.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

@SuppressWarnings("ALL")
@Component
@Path("/building")
public class BuildingRequest extends AbstractRequest<Building> implements BuildingRequestInterface {

    @Autowired
    private transient BuildingService buildingService;

    @Autowired
    private transient GoodService goodService;

    @Autowired
    private transient CostService costService;

    @Override
    protected BuildingService getService() {
        return buildingService;
    }

    @Override
    @GET
    @Path("/")
    public DtoList<Building> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
                                @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size, @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                                @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
        return super.getAll(first, size, sort, desc);
    }

    @Override
    @GET
    @Path("/{id}")
    public Building getById(@PathParam("id") final long id) {
        return getService().findById(id);
    }

    @Override
    @PUT
    @Path("/{id}")
    public Building update(@PathParam("id") final long id, final Building dto) {
        return getService().update(id, dto);
    }

    @Override
    @POST
    @Path("/")
    public Building create(final Building dto) {
        return getService().create(dto);
    }

    @Override
    @GET
    @Path("/{buildingId}/cost")
    public DtoList<Cost> getCosts(@PathParam("buildingId") final long buildingId,
                           @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
                           @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
                           @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                           @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {

        final LimitParams limitParams = LimitParams.create(first, size);
        final SortParams sortParams = SortParams.create(sort, desc);

        final List<Cost> data = costService.findByBuyableId(buildingId, limitParams, sortParams);
        final long total = costService.getCount();

        return new DtoList<>(data, total, limitParams);
    }

    @Override
    @GET
    @Path("/{buildingId}/cost/addable")
    public DtoList<Good> getAddableCosts(@PathParam("buildingId") final long buildingId,
                                  @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
                                  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
                                  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
                                  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {


        final LimitParams limitParams = LimitParams.create(first, size);
        final SortParams sortParams = SortParams.create(sort, desc);
        final long total = goodService.getCount();
        final List<Good> data = goodService.findAll(limitParams, sortParams);

        return new DtoList<>(data, total, limitParams);

    }

    @Override
    @POST
    @Path("/{buildingId}/cost")
    public Cost createCost(@PathParam("buildingId") final long buildingId, final Cost dto) {
        return null;
    }

    @Override
    @DELETE
    @Path("/{buildingId}/cost/{goodId}")
    public Cost deleteCost(@PathParam("buildingId") final long buildingId, @PathParam("goodId") final long goodId) {
        return null;
    }

    @Override
    @PUT
    @Path("/{buildingId}")
    public Cost updateCost(@PathParam("buildingId") final long buildingId, final Cost dto) {
        return null;
    }


}
