package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;
/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 26.01.2015.
 */
@PreAuthorize(HAS_ROLE_ADMIN_BUYABLE_REQUIREMENT)
public interface RequirementService {

	Requirement create(Requirement dto);

	Requirement update(Requirement dto);

	void delete(long buyableId, long goodId);

    ResultList<Requirement> findByBuyableId(final long buyableId);

	ResultList<Requirement> findByBuyableId(final long buyableId, final LimitParams limitParams, final SortParams sortParams);

}
