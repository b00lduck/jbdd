package com.nigames.jbdd.service.service.item.facet;

import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;

import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 29.01.2015.
 */
public interface BuyableFacetService {

	List<Good> getAddableCostGoods(long buyableId, LimitParams limitParams, SortParams sortParams);

	long getAddableCostGoodsCount(long buyableId);


}
