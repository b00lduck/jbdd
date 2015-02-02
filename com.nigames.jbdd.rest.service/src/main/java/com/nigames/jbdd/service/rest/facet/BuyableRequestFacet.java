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
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

        final List<Cost> data = costService.findByBuyableId(itemId, limitParams, sortParams);
        final long total = costService.getCount();

        return new DtoList<>(data, total, limitParams);
    }

	@Override
	public DtoList<Good> getAddableCostGoods(final long itemId,
	                                         final Long first, final Long size,
	                                         final String sort, final Boolean desc) {

        final LimitParams limitParams = LimitParams.create(first, size);
        final SortParams sortParams = SortParams.create(sort, desc);

	    final long total = buyableFacetService.getAddableCostGoodsCount(itemId);
	    final List<Good> data = buyableFacetService.getAddableCostGoods(itemId, limitParams, sortParams);

        return new DtoList<>(data, total, limitParams);
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

		final List<Requirement> data = requirementService.findByBuyableId(buyableId, limitParams, sortParams);
		final long total = requirementService.getCount();

		return new DtoList<>(data, total, limitParams);
	}

	@Override
	public DtoList<Buyable> getAddableRequirementBuyables(final long itemId,
	                                                      final Long first, final Long size,
	                                                      final String sort, final Boolean desc) {

		final LimitParams limitParams = LimitParams.create(first, size);
		final SortParams sortParams = SortParams.create(sort, desc);

		final long total = buyableFacetService.getAddableRequirementBuyablesCount(itemId);
		final List<Buyable> data = buyableFacetService.getAddableRequirementBuyables(itemId, limitParams, sortParams);

		return new DtoList<>(data, total, limitParams);
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
