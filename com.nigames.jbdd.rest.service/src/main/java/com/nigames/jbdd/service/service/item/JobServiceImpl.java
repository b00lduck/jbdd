package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.JobEntity;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.JobConversionService;
import com.nigames.jbdd.service.repository.JobRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * JobService implementation.
 *
 * @author Daniel
 * @see JobServiceImpl
 */
@Service
public class JobServiceImpl extends AbstractRepositoryBackedService<JobEntity, Long, Job>
		implements JobService {

	@Autowired
	private JobConversionService buildingConversionService;

	@Autowired
	private JobRepository buildingRepository;

	@Override
	protected JobRepository getRepository() {
		return buildingRepository;
	}

	@Override
	protected ConversionServiceInterface<JobEntity, Job> getConversionService() {
		return buildingConversionService;
	}

}
