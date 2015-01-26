package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.CostRequestInterface;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import com.nigames.jbdd.service.service.subitem.buyable.CostService;
import com.nigames.jbdd.statics.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@SuppressWarnings("ALL")
@Component
@Path("/cost")
public class CostRequest extends AbstractRequest<Cost> implements CostRequestInterface {

	@Autowired
	private transient CostService costService;

	@Autowired
	private transient GoodService goodService;

	@Override
	protected CostService getService() {
		return costService;
	}

	@Override
	@GET
	@Path("/")
	public DtoList<Cost> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                            @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                            @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                            @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return super.getAll(first, size, sort, desc);
	}

	@Override
	@GET
	@Path("/{id}")
	public Cost getById(@PathParam("id") final long id) {
		return getService().findById(id);
	}

	@Override
	@PUT
	@Path("/{id}")
	public Cost update(@PathParam("id") final long id, final Cost dto) {
		return getService().update(id, dto);
	}

	@Override
	@POST
	@Path("/")
	public Cost create(final Cost dto) {
		return getService().create(dto);
	}

	@Override
	@GET
	@Path("/buyable/{id}")
	public DtoList<Cost> getCostsForBuyable(@PathParam("id") final long id,
	                                        @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                        @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                        @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                        @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {

		final DtoList<Cost> ret = new DtoList<>();

		ret.setData(costService.findByBuyableId(id, LimitParams.create(first, size), SortParams.create(sort, desc)));

		final Long total = costService.getCountByBuyableId(id);

		ret.setMeta(AbstractRequest.createMeta(total, first, size));

		return ret;

	}

	@GET
	@Path("/buyable/{id}/addable")
	public DtoList<Good> getAddable(@PathParam("id") final long id,
	                                @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return null;
		//goodService.findAll(LimitParams.createDefault(), SortParams.createDefault());
	}

}
