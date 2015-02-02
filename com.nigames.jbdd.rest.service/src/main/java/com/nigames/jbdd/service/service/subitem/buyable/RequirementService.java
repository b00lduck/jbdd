package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

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

	List<Requirement> findAll(final LimitParams limitParams, final SortParams sortParams);

	long getCount();

	List<Requirement> findByBuyableId(final long buyableId);

	List<Requirement> findByBuyableId(final long buyableId, final LimitParams limitParams, final SortParams sortParams);

	long getCountByBuyableId(final long buyableId);

}
