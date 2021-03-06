package com.nigames.jbdd.service.service.item.facet;

import com.nigames.jbdd.rest.dto.*;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.rest.dto.facet.Identifiable;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.service.service.item.TechnologyService;
import com.nigames.jbdd.service.service.subitem.buyable.CostService;
import com.nigames.jbdd.service.service.subitem.buyable.CyclicRequirementException;
import com.nigames.jbdd.service.service.subitem.buyable.RequirementService;
import com.nigames.jbdd.types.ResultList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
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
	private TechnologyService technologyService;

	@Autowired
	private GoodService goodService;

	@Autowired
	private RequirementService requirementService;

	@Autowired
	private CostService costService;

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
	public ResultList<Buyable> getAddableRequirementBuyables(final long buyableId) throws CyclicRequirementException {

		final List<Building> buildingList = buildingService.findAllEnabled();
		final List<Technology> technologyList = technologyService.findAllEnabled();

		final List<Buyable> buyableList = buildingList.stream().collect(Collectors.toList());
		buyableList.addAll(technologyList.stream().collect(Collectors.toList()));

		final List<Buyable> ret = new ArrayList<>();

		final Set<Long> requirementsForBuyable = getRequirementsForBuyable(buyableId);

		for (final Buyable b : buyableList) {

			// Cannot add self
			if (((Identifiable) b).getId() == buyableId) {
				continue;
			}

			// Cannot add already added requirements
			if (requirementsForBuyable.contains(((Identifiable) b).getId())) {
				continue;
			}

			// Cannot create cyclic requirements
			final Identifiable identifiable = (Identifiable) b;
			final long id = identifiable.getId();
			if (getRequirementsForBuyableRecursive(id, new HashSet<>()).contains(buyableId)) {
				continue;
			}

			ret.add(b);

		}

		return ResultList.create(ret);
	}

	private Set<Long> getRequirementsForBuyableRecursive(final long buyableId, final Set<Long> visitedItems) {

		if (visitedItems.contains(buyableId)) {
			throw new CyclicRequirementException();
		}

		visitedItems.add(buyableId);

		final Set<Long> ret = new HashSet<>();

		final List<Requirement> reqList = requirementService.findByBuyableId(buyableId);

		for (final Requirement r : reqList) {
			ret.add(r.getRequiredBuyableId());
			ret.addAll(getRequirementsForBuyableRecursive(r.getRequiredBuyableId(), visitedItems));
		}

		return ret;
	}

	private Set<Long> getRequirementsForBuyable(final long buyableId) {

		final Set<Long> ret = new HashSet<>();

		final List<Requirement> reqList = requirementService.findByBuyableId(buyableId);

		final Collection<Long> collection = reqList.stream()
				.map(Requirement::getRequiredBuyableId)
				.collect(Collectors.toList());
		ret.addAll(collection);

		return ret;
	}

	private Set<Long> getCostsForBuyable(final long buyableId) {

		final Set<Long> ret = new HashSet<>();

		final ResultList<Cost> reqList = costService.findByBuyableId(buyableId);

		final Collection<Long> collection = reqList.stream()
				.map(Cost::getGoodId)
				.collect(Collectors.toList());
		ret.addAll(collection);

		return ret;
	}


}
