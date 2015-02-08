package com.nigames.jbdd.service.rest.facet;

import com.nigames.jbdd.rest.api.aspect.BuyableRequestInterface;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.service.service.item.facet.BuyableFacetService;
import com.nigames.jbdd.service.service.subitem.buyable.CostService;
import com.nigames.jbdd.service.service.subitem.buyable.RequirementService;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuyableRequestFacet implements BuyableRequestInterface {

    @Autowired
    private CostService costService;

	@Autowired
	private RequirementService requirementService;

	@Autowired
	private BuyableFacetService buyableFacetService;

	@Override
	public DtoList<Cost> getCosts(final long itemId,
                                  final Long first, final Long size,
                                  final String sort, final Boolean desc) {

        final LimitParams limitParams = LimitParams.create(first, size);
        final SortParams sortParams = SortParams.create(sort, desc);

        final ResultList<Cost> data = costService.findByBuyableId(itemId, limitParams, sortParams);

        return new DtoList<>(data, limitParams);
    }

	@Override
	public DtoList<Good> getAddableCostGoods(final long itemId,
	                                         final Long first, final Long size,
	                                         final String sort, final Boolean desc) {

        final LimitParams limitParams = LimitParams.create(first, size);
		//final SortParams sortParams = SortParams.create(sort, desc);

    	final ResultList<Good> data = buyableFacetService.getAddableCostGoods(itemId);

        return new DtoList<>(data, limitParams);
    }

	@Override
	public Cost createCost(final long buyableId, final Cost dto) {
		checkConsistency(buyableId, dto);
		return costService.create(dto);
	}

	@Override
	public void deleteCost(final long buyableId, final long goodId) {
		costService.delete(buyableId, goodId);
	}

	@Override
	public Cost updateCost(final long buyableId, final Cost dto) {
		checkConsistency(buyableId, dto);
		return costService.update(dto);
	}

	@Override
	public DtoList<Requirement> getRequirements(final long buyableId,
	                                            final Long first, final Long size,
	                                            final String sort, final Boolean desc) {

		final LimitParams limitParams = LimitParams.create(first, size);
		final SortParams sortParams = SortParams.create(sort, desc);

		final ResultList<Requirement> data = requirementService.findByBuyableId(buyableId, limitParams, sortParams);

		return new DtoList<>(data, limitParams);
	}

	@Override
	public DtoList<Buyable> getAddableRequirementBuyables(final long itemId,
	                                                      final Long first, final Long size,
	                                                      final String sort, final Boolean desc) {

		final LimitParams limitParams = LimitParams.create(first, size);
		// final SortParams sortParams = SortParams.create(sort, desc);

		final ResultList<Buyable> data = buyableFacetService.getAddableRequirementBuyables(itemId);

		return new DtoList<>(data, limitParams);
	}

	@Override
	public Requirement createRequirement(final long buyableId, final Requirement dto) {
		checkConsistency(buyableId, dto);
		return requirementService.create(dto);
	}

	@Override
	public void deleteRequirement(final long buyableId, final long requiredBuyableId) {
		requirementService.delete(buyableId, requiredBuyableId);
	}

	@Override
	public Requirement updateRequirement(final long buyableId, final Requirement dto) {
		checkConsistency(buyableId, dto);
		return requirementService.update(dto);
	}

	private void checkConsistency(final long buyableId, final Cost dto) {
		if (buyableId != dto.getBuyableId()) {
			throw new IllegalArgumentException("buyableId must match the buyableId in DTO");
		}
	}

	private void checkConsistency(final long buyableId, final Requirement dto) {
		//TODO: check consistency
		/*
		if (buyableId != dto.getBuyableId()) {
			throw new IllegalArgumentException("buyableId must match the buyableId in DTO");
		}
		*/
	}

}
