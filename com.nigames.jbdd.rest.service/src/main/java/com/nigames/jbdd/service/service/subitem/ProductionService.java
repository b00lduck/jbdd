package com.nigames.jbdd.service.service.subitem;

import com.nigames.jbdd.rest.dto.Production;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.nigames.jbdd.service.service.SecurityElConstants.HAS_ROLE_ADMIN_BUILDING_PRODUCTION;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 08.02.2015.
 */
@PreAuthorize(HAS_ROLE_ADMIN_BUILDING_PRODUCTION)
public interface ProductionService {

	Production create(Production dto);

	Production update(Production dto);

	void delete(long buildingId, long goodId);

	ResultList<Production> findByBuildingId(final long buildingId);

	ResultList<Production> findByBuildingId(final long buildingId, final LimitParams limitParams,
	                                        final SortParams sortParams);

}
