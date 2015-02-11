package com.nigames.jbdd.service.service.subitem;

import com.nigames.jbdd.rest.dto.Production;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.SortParams;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.nigames.jbdd.service.service.SecurityElConstants.HAS_ROLE_ADMIN_JOB_PRODUCTION;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 08.02.2015.
 */
@PreAuthorize(HAS_ROLE_ADMIN_JOB_PRODUCTION)
public interface ProductionService {

	Production create(Production dto);

	Production update(Production dto);

	void delete(long jobId, long goodId);

	ResultList<Production> findByJobId(final long jobId);

	ResultList<Production> findByJobId(final long jobId, final LimitParams limitParams,
	                                        final SortParams sortParams);

}
