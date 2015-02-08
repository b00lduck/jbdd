package com.nigames.jbdd.service.service.item.facet;

import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.rest.dto.facet.Identifiable;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.service.service.subitem.buyable.CostService;
import com.nigames.jbdd.service.service.subitem.buyable.RequirementService;
import com.nigames.jbdd.types.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 29.01.2015.
 */
@Component
public class BuyableFacetServiceImpl implements BuyableFacetService {

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private GoodService goodService;

	@Autowired
	private RequirementService requirementService;

	@Autowired
	private CostService costService;


	@Override
	public long getAddableCostGoodsCount(final long buyableId) {
		return 0;
	}

	@Override
	public ResultList<Good> getAddableCostGoods(final long buyableId) {

		final List<Good> goodList = goodService.findAllEnabled();

		final List<Good> ret = new ArrayList<>();

		final Set<Long> costGoodList = getCostsForBuyable(buyableId);

		for (final Good g : goodList) {

			// Cannot add already added costs
			if (costGoodList.contains(g.getId())) {
				continue;
			}

			ret.add(g);

		}

        return ResultList.create(ret);
	}

	@Override
	public ResultList<Buyable> getAddableRequirementBuyables(final long buyableId) {

		final List<Building> buildingList = buildingService.findAllEnabled();
		@SuppressWarnings("unchecked")
		final List<Buyable> buyableList = (List) buildingList;

		final List<Buyable> ret = new ArrayList<>();

		// TODO: pull getRequirementsForBuyable out!

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

		return ResultList.create(ret);
	}

	private Set<Long> getRequirementsForBuyableRecursive(final long buyableId) {

		// TODO: prevent endless loop in case of cyclic requirements in DB

		final Set<Long> ret = new HashSet<>();

		final List<Requirement> reqList = requirementService.findByBuyableId(buyableId).getList();

		for (final Requirement r : reqList) {
			ret.add(r.getRequiredBuyableId());
			ret.addAll(getRequirementsForBuyableRecursive(r.getRequiredBuyableId()));
		}

		return ret;
	}

	private Set<Long> getRequirementsForBuyable(final long buyableId) {

		final Set<Long> ret = new HashSet<>();

		final List<Requirement> reqList = requirementService.findByBuyableId(buyableId).getList();

		ret.addAll(reqList.stream().map(Requirement::getRequiredBuyableId).collect(Collectors.toList()));

		return ret;
	}

	private Set<Long> getCostsForBuyable(final long buyableId) {

		final Set<Long> ret = new HashSet<>();

		final ResultList<Cost> reqList = costService.findByBuyableId(buyableId);

        // TODO think about syntax vs. performance
		ret.addAll(reqList.getList().stream().map(Cost::getGoodId).collect(Collectors.toList()));

		return ret;
	}


}
