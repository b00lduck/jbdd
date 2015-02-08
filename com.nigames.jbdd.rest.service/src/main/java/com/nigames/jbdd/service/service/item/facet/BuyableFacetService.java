package com.nigames.jbdd.service.service.item.facet;

import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.types.ResultList;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 29.01.2015.
 */
@PreAuthorize(FORBID_ALL)
public interface BuyableFacetService {

	@PreAuthorize(HAS_ROLE_ADMIN_BUYABLE_COST)
    ResultList<Good> getAddableCostGoods(long buyableId);

    @PreAuthorize(HAS_ROLE_ADMIN_BUYABLE_REQUIREMENT)
    ResultList<Buyable> getAddableRequirementBuyables(final long buyableId);

}
