package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.nigames.jbdd.service.service.SecurityElConstants.HAS_ROLE_ADMIN_BUYABLE_COST;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 26.01.2015.
 */
@PreAuthorize(HAS_ROLE_ADMIN_BUYABLE_COST)
public interface CostService {

	Cost create(Cost dto);

	Cost update(Cost dto);

	void delete(long buyableId, long goodId);

    ResultList<Cost> findByBuyableId(final long buyableId);

	ResultList<Cost> findByBuyableId(final long buyableId, final LimitParams limitParams, final SortParams sortParams);

}
