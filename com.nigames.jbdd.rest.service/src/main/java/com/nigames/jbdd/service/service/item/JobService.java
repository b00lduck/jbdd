package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.service.service.AbstractDtoServiceInterface;
import org.springframework.security.access.prepost.PreAuthorize;

import static com.nigames.jbdd.service.service.SecurityElConstants.HAS_ROLE_ADMIN_JOB;
/**
 * JobService interface.
 *
 * @author Daniel
 * @see JobServiceImpl
 */
@PreAuthorize(HAS_ROLE_ADMIN_JOB)
public interface JobService extends AbstractDtoServiceInterface<Job> {

}
