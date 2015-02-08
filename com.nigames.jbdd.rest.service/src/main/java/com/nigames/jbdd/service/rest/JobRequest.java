package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.JobRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.service.service.item.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class JobRequest extends AbstractRequest<Job> implements JobRequestInterface {

	@Autowired
	private transient JobService jobService;

	@Override
	protected JobService getService() {
		return jobService;
	}

	@Override
	public DtoList<Job> getAll(final Long first, final Long size, final String sort, final Boolean desc) {
		return super.getAll(first, size, sort, desc);
	}

	@Override
	public Job getById(final long id) {
		return getService().findById(id);
	}

	@Override
	public Job update(final long id, final Job dto) {
		return getService().update(id, dto);
	}

	@Override
	public Job create(final Job dto) {
		return getService().create(dto);
	}

}
