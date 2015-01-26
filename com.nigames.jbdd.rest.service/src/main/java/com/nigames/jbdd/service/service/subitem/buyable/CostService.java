package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.nigames.jbdd.rest.dto.UserRole.ROLE_ADMIN_COST;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 26.01.2015.
 */
@PreAuthorize("hasRole('ROLE_EXCLUDE_ALL')")
public interface CostService extends AbstractDtoServiceInterface<Cost, CostEntity> {

	@Override
	@PreAuthorize("hasAnyRole('" + ROLE_ADMIN_COST + "')")
	Cost create(Cost dto);

	@Override
	@PreAuthorize("hasRole('" + ROLE_ADMIN_COST + "')")
	void delete(long id);

	@Override
	@PreAuthorize("hasRole('" + ROLE_ADMIN_COST + "')")
	Cost findById(long entityId);

	@Override
	@PreAuthorize("hasRole('" + ROLE_ADMIN_COST + "')")
	long getCount();

	@Override
	@PreAuthorize("hasRole('" + ROLE_ADMIN_COST + "')")
	List<Cost> findAll(final LimitParams limitParams, final SortParams sortParams);

	@PreAuthorize("hasRole('" + ROLE_ADMIN_COST + "')")
	List<Cost> findByBuyableId(final long buyableId, final LimitParams limitParams, final SortParams sortParams);

	@PreAuthorize("hasRole('" + ROLE_ADMIN_COST + "')")
	long getCountByBuyableId(final long buyableId);

}
