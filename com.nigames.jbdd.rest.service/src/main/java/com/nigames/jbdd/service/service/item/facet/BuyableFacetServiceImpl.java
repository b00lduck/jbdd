package com.nigames.jbdd.service.service.item.facet;

import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.rest.dto.facet.Identifiable;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.service.service.querystrategy.AddableCostQueryStrategy;
import com.nigames.jbdd.service.service.subitem.buyable.RequirementService;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 29.01.2015.
 */
@Component
public class BuyableFacetServiceImpl implements BuyableFacetService {

	@Autowired
	private GoodService goodService;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private RequirementService requirementService;

	@Autowired
	private AddableCostQueryStrategy addableCostGoodQueryStrategy;

	@Override
	public List<Good> getAddableCostGoods(final long buyableId, final LimitParams limitParams,
	                                      final SortParams sortParams) {
		return goodService.findAll(limitParams, sortParams, addableCostGoodQueryStrategy, buyableId);
	}

	@Override
	public long getAddableCostGoodsCount(final long buyableId) {
		return goodService.getCount(addableCostGoodQueryStrategy, buyableId);
	}

	private Set<Long> getRequirementsForBuyableRecursive(final long buyableId) {

		// TODO: prevent endless loop in case of cyclic requirements in DB

		final Set<Long> ret = new HashSet<>();

		final List<Requirement> reqList = requirementService.findByBuyableId(buyableId);

		for (final Requirement r : reqList) {
			ret.add(r.getRequiredBuyableId());
			ret.addAll(getRequirementsForBuyableRecursive(r.getRequiredBuyableId()));
		}

		return ret;
	}

	private Set<Long> getRequirementsForBuyable(final long buyableId) {

		final Set<Long> ret = new HashSet<>();

		final List<Requirement> reqList = requirementService.findByBuyableId(buyableId);

		for (final Requirement r : reqList) {
			ret.add(r.getRequiredBuyableId());
		}

		return ret;
	}

	@Override
	public List<Buyable> getAddableRequirementBuyables(final long buyableId, final LimitParams limitParams, final SortParams sortParams) {

		final List<Building> buildingList = buildingService.findAll(LimitParams.createDefault(), SortParams.createDefault());
		@SuppressWarnings("unchecked") final
		List<Buyable> buyableList = (List) buildingList;

		final List<Buyable> ret = new ArrayList<>();

		for (final Buyable b : buyableList) {

			// Cannot add self
			if (((Identifiable) b).getId() == buyableId) {
				continue;
			}

			// Cannot add already added requirements
			if (getRequirementsForBuyable(buyableId).contains(((Identifiable) b).getId())) {
				continue;
			}

			// Cannot create cyclic requirements
			if (getRequirementsForBuyableRecursive(((Identifiable) b).getId()).contains(buyableId)) {
				continue;
			}

			ret.add(b);

		}

		return ret;
	}

	@Override
	public long getAddableRequirementBuyablesCount(final long buyableId) {
		return buildingService.getCount();
	}
}
