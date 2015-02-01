package com.nigames.jbdd.service.service.item.facet;

import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.service.service.item.BuildingService;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.service.service.querystrategy.AddableCostQueryStrategy;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
	private AddableCostQueryStrategy addableCostQueryStrategy;

	@Override
	public List<Good> getAddableCostGoods(final long buyableId, final LimitParams limitParams,
	                                      final SortParams sortParams) {
		return goodService.findAll(limitParams, sortParams, addableCostQueryStrategy, buyableId);
	}

	@Override
	public long getAddableCostGoodsCount(final long buyableId) {
		return goodService.getCount(addableCostQueryStrategy, buyableId);
	}

	@Override
	public List<Buyable> getAddableRequirementBuyables(long buyableId, LimitParams limitParams, SortParams sortParams) {

		List<Building> buildingList = buildingService.findAll(limitParams, sortParams);
		@SuppressWarnings("unchecked")
		List<Buyable> buyableList = (List) buildingList;

		return buyableList;
	}

	@Override
	public long getAddableRequirementBuyablesCount(long buyableId) {
		return buildingService.getCount();
	}
}
