package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.nigames.jbdd.rest.dto.UserRole.ROLE_ADMIN_REQUIREMENT;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 26.01.2015.
 */
@PreAuthorize("hasRole('ROLE_EXCLUDE_ALL')")
public interface RequirementService {

	@PreAuthorize("hasAnyRole('" + ROLE_ADMIN_REQUIREMENT + "')")
	Requirement create(Requirement dto);

	@PreAuthorize("hasAnyRole('" + ROLE_ADMIN_REQUIREMENT + "')")
	Requirement update(Requirement dto);

	@PreAuthorize("hasRole('" + ROLE_ADMIN_REQUIREMENT + "')")
	void delete(long buyableId, long goodId);

	@PreAuthorize("hasRole('" + ROLE_ADMIN_REQUIREMENT + "')")
	List<Requirement> findAll(final LimitParams limitParams, final SortParams sortParams);

	@PreAuthorize("hasRole('" + ROLE_ADMIN_REQUIREMENT + "')")
	long getCount();

	@PreAuthorize("hasRole('" + ROLE_ADMIN_REQUIREMENT + "')")
	List<Requirement> findByBuyableId(final long buyableId);

	@PreAuthorize("hasRole('" + ROLE_ADMIN_REQUIREMENT + "')")
	List<Requirement> findByBuyableId(final long buyableId, final LimitParams limitParams, final SortParams sortParams);

	@PreAuthorize("hasRole('" + ROLE_ADMIN_REQUIREMENT + "')")
	long getCountByBuyableId(final long buyableId);

}
