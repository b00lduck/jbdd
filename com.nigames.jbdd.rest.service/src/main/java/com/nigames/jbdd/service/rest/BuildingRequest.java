package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.BuildingRequestInterface;
import com.nigames.jbdd.rest.dto.*;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.service.rest.facet.BuyableRequestFacet;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.service.service.subitem.ProductionService;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingRequest extends AbstractRequest<Building> implements BuildingRequestInterface {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private ProductionService productionService;

	@Autowired
	private BuyableRequestFacet buyableRequestFacet;

	@SuppressWarnings("SuspiciousGetterSetter")

	@Override
	protected BuildingService getService() {
		return buildingService;
	}

	@Override
	public DtoList<Building> getAll(final Long first, final Long size, final String sort, final Boolean desc) {
		return super.getAll(first, size, sort, desc);
	}

	@Override
	public Building getById(final long id) {
		return getService().findById(id);
	}

	@Override
	public Building update(final long id, final Building dto) {
		return getService().update(id, dto);
	}

	@Override
	public Building create(final Building dto) {
		return getService().create(dto);
	}

	@Override
	public DtoList<Cost> getCosts(final long buildingId, final Long first, final Long size, final String sort,
	                              final Boolean desc) {
		return buyableRequestFacet.getCosts(buildingId, first, size, sort, desc);
	}

	@Override
	public DtoList<Good> getAddableCostGoods(final long buildingId, final Long first, final Long size, final String sort,
	                                         final Boolean desc) {
		return buyableRequestFacet.getAddableCostGoods(buildingId, first, size, sort, desc);
	}

	@Override
	public Cost createCost(final long buildingId, final Cost dto) {
		return buyableRequestFacet.createCost(buildingId, dto);
	}

	@Override
	public void deleteCost(final long buildingId, final long goodId) {
		buyableRequestFacet.deleteCost(buildingId, goodId);
	}

	@Override
	public Cost updateCost(final long buildingId, final Cost dto) {
		return buyableRequestFacet.updateCost(buildingId, dto);
	}

	@Override
	public DtoList<Requirement> getRequirements(final long buildingId, final Long first,
	                                            final Long size, final String sort, final Boolean desc) {
		return buyableRequestFacet.getRequirements(buildingId, first, size, sort, desc);
	}

	@Override
	public DtoList<Buyable> getAddableRequirementBuyables(final long buildingId, final Long first, final Long size,
	                                                      final String sort, final Boolean desc) {
		return buyableRequestFacet.getAddableRequirementBuyables(buildingId, first, size, sort, desc);
	}

	@Override
	public Requirement createRequirement(final long buildingId, final Requirement dto) {
		return buyableRequestFacet.createRequirement(buildingId, dto);
	}

	@Override
	public void deleteRequirement(final long buildingId, final long requiredBuyableId) {
		buyableRequestFacet.deleteRequirement(buildingId, requiredBuyableId);
	}

	@Override
	public Requirement updateRequirement(final long buildingId, final Requirement dto) {
		return buyableRequestFacet.updateRequirement(buildingId, dto);
	}


	@Override
	public DtoList<Production> getProductions(long buildingId, Long first, Long size, String sort, Boolean desc) {

		final LimitParams limitParams = LimitParams.create(first, size);
		final SortParams sortParams = SortParams.create(sort, desc);

		final ResultList<Production> data = productionService.findByBuildingId(buildingId, limitParams, sortParams);

		return new DtoList<>(data, limitParams);
	}

	@Override
	public DtoList<Good> getAddableProductionGoods(long buildingId, Long first, Long size, String sort, Boolean desc) {

		final LimitParams limitParams = LimitParams.create(first, size);
		//final SortParams sortParams = SortParams.create(sort, desc);

		// TODO: sorting

		final ResultList<Good> data = buildingService.getAddableProductionGoods(buildingId);

		return new DtoList<>(data, limitParams);

	}

	@Override
	public Production createProduction(long buildingId, Production dto) {
		checkConsistency(buildingId, dto);
		return productionService.create(dto);
	}

	@Override
	public void deleteProduction(long buildingId, long goodId) {
		productionService.delete(buildingId, goodId);
	}

	@Override
	public Production updateProduction(long buildingId, Production dto) {
		checkConsistency(buildingId, dto);
		return productionService.update(dto);
	}

	private void checkConsistency(final long buildingId, final Production dto) {
		if (buildingId != dto.getBuildingId()) {
			throw new IllegalArgumentException("buildingId must match the buildingId in DTO");
		}
	}

}
