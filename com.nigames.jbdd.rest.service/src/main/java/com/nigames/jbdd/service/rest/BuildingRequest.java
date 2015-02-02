package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.BuildingRequestInterface;
import com.nigames.jbdd.rest.dto.*;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.service.rest.facet.BuyableRequestFacet;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.statics.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;

@Component
@Path("/building")
public class BuildingRequest extends AbstractRequest<Building> implements BuildingRequestInterface {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private BuyableRequestFacet buyableRequestFacet;

	@SuppressWarnings("SuspiciousGetterSetter")
	@Override
	protected BuildingService getService() {
		return buildingService;
	}

	@Override
	@GET
	@Path("/")
	public DtoList<Building> getAll(@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
								@QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
								@QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
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
	@Path("/{itemId}/cost")
	public DtoList<Cost> getCosts(@PathParam("itemId") final long itemId,
								  @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
								  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
								  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
								  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return buyableRequestFacet.getCosts(itemId, first, size, sort, desc);
	}

	@Override
	@GET
	@Path("/{itemId}/cost/addable")
	public DtoList<Good> getAddableCostGoods(@PathParam("itemId") final long itemId,
											 @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
											 @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
											 @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
											 @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return buyableRequestFacet.getAddableCostGoods(itemId, first, size, sort, desc);
	}

	@Override
	@POST
	@Path("/{itemId}/cost")
	public Cost createCost(@PathParam("itemId") final long itemId, final Cost dto) {
		return buyableRequestFacet.createCost(itemId, dto);
	}

	@Override
	@DELETE
	@Path("/{itemId}/cost/{goodId}")
	public void deleteCost(@PathParam("itemId") final long itemId, @PathParam("goodId") final long goodId) {
		buyableRequestFacet.deleteCost(itemId, goodId);
	}

	@Override
	@PUT
	@Path("/{itemId}/cost")
	public Cost updateCost(@PathParam("itemId") final long itemId, final Cost dto) {
		return buyableRequestFacet.updateCost(itemId, dto);
	}

	@Override
	@GET
	@Path("/{itemId}/requirement")
	public DtoList<Requirement> getRequirements(@PathParam("itemId") final long itemId,
												@QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
												@QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
												@QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
												@QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return buyableRequestFacet.getRequirements(itemId, first, size, sort, desc);
	}

	@Override
	@GET
	@Path("/{itemId}/requirement/addable")
	public DtoList<Buyable> getAddableRequirementBuyables(@PathParam("itemId") final long itemId,
														  @QueryParam(Constants.QUERY_PARAM_FIRST) final Long first,
														  @QueryParam(Constants.QUERY_PARAM_SIZE) final Long size,
														  @QueryParam(Constants.QUERY_PARAM_SORT) final String sort,
														  @QueryParam(Constants.QUERY_PARAM_DESC) final Boolean desc) {
		return buyableRequestFacet.getAddableRequirementBuyables(itemId, first, size, sort, desc);
	}

	@Override
	@POST
	@Path("/{itemId}/requirement")
	public Requirement createRequirement(@PathParam("itemId") final long itemId, final Requirement dto) {
		return buyableRequestFacet.createRequirement(itemId, dto);
	}

	@Override
	@DELETE
	@Path("/{itemId}/requirement/{requiredBuyableId}")
	public void deleteRequirement(@PathParam("itemId") final long itemId,
								  @PathParam("requiredBuyableId") final long requiredBuyableId) {
		buyableRequestFacet.deleteRequirement(itemId, requiredBuyableId);
	}

	@Override
	@PUT
	@Path("/{itemId}/requirement/")
	public Requirement updateRequirement(@PathParam("itemId") final long itemId, final Requirement dto) {
		return buyableRequestFacet.updateRequirement(itemId, dto);
	}

}
