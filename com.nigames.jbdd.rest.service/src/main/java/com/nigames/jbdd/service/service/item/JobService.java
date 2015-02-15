package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import com.nigames.jbdd.types.ResultList;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

import static com.nigames.jbdd.service.service.SecurityElConstants.*;

/**
 * JobService interface.
 *
 * @author Daniel
 * @see JobServiceImpl
 */
@PreAuthorize(HAS_ROLE_ADMIN_JOB)
public interface JobService extends AbstractDtoServiceInterface<Job> {

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_JOB)
	Job findById(final Long entityId);

	@Override
	@PreAuthorize(HAS_ROLE_SYSTEM_OR_ADMIN_JOB)
	Job create(final Job dto);

	@PreAuthorize(HAS_ROLE_ADMIN_JOB_PRODUCTION)
	ResultList<Good> getAddableProductionGoods(long jobId);

	@PreAuthorize(HAS_ROLE_ADMIN_JOB)
	List<Job> findAllEnabled();

}
