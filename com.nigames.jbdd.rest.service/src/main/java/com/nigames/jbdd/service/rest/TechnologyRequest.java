package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.TechnologyRequestInterface;
import com.nigames.jbdd.rest.dto.*;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.service.rest.facet.BuyableRequestFacet;
import com.nigames.jbdd.service.service.item.TechnologyService;
import com.nigames.jbdd.statics.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Component
public class TechnologyRequest extends AbstractRequest<Technology> implements TechnologyRequestInterface {

	@Autowired
	private TechnologyService technologyService;

	@Autowired
	private BuyableRequestFacet buyableRequestFacet;

	@SuppressWarnings("SuspiciousGetterSetter")
	@Override
	protected TechnologyService getService() {
		return technologyService;
	}

	@Override
	public DtoList<Technology> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return super.getAll(first, size, sort, desc);
	}

	@Override
	public Technology getById(@PathParam("id") final long id) {
		return getService().findById(id);
	}

	@Override
	public Technology update(@PathParam("id") final long id, final Technology dto) {
		return getService().update(id, dto);
	}

	@Override
	public Technology create(final Technology dto) {
		return getService().create(dto);
	}

	@Override
	public DtoList<Cost> getCosts(@PathParam("itemId") final long itemId,
	                              @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                              @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                              @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                              @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return buyableRequestFacet.getCosts(itemId, first, size, sort, desc);
	}

	@Override
	public DtoList<Good> getAddableCostGoods(@PathParam("itemId") final long itemId,
	                                         @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                         @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                         @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                         @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return buyableRequestFacet.getAddableCostGoods(itemId, first, size, sort, desc);
	}

	@Override
	public Cost createCost(@PathParam("itemId") final long itemId, final Cost dto) {
		return buyableRequestFacet.createCost(itemId, dto);
	}

	@Override
	public void deleteCost(@PathParam("itemId") final long itemId, @PathParam("goodId") final long goodId) {
		buyableRequestFacet.deleteCost(itemId, goodId);
	}

	@Override
	public Cost updateCost(@PathParam("itemId") final long itemId, final Cost dto) {
		return buyableRequestFacet.updateCost(itemId, dto);
	}

	@Override
	public DtoList<Requirement> getRequirements(@PathParam("itemId") final long itemId,
	                                            @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                            @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                            @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                            @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return buyableRequestFacet.getRequirements(itemId, first, size, sort, desc);
	}

	@Override
	public DtoList<Buyable> getAddableRequirementBuyables(@PathParam("itemId") final long itemId,
	                                                      @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
	                                                      @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
	                                                      @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
	                                                      @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return buyableRequestFacet.getAddableRequirementBuyables(itemId, first, size, sort, desc);
	}

	@Override
	public Requirement createRequirement(@PathParam("itemId") final long itemId, final Requirement dto) {
		return buyableRequestFacet.createRequirement(itemId, dto);
	}

	@Override
	public void deleteRequirement(@PathParam("itemId") final long itemId,
	                              @PathParam("requiredBuyableId") final long requiredBuyableId) {
		buyableRequestFacet.deleteRequirement(itemId, requiredBuyableId);
	}

	@Override
	public Requirement updateRequirement(@PathParam("itemId") final long itemId, final Requirement dto) {
		return buyableRequestFacet.updateRequirement(itemId, dto);
	}

}
