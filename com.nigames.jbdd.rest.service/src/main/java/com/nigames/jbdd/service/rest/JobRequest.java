package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.JobRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.rest.dto.Production;
import com.nigames.jbdd.service.service.item.JobService;
import com.nigames.jbdd.service.service.subitem.ProductionService;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class JobRequest extends AbstractRequest<Job> implements JobRequestInterface {

	@Autowired
	private transient JobService jobService;

	@Autowired
	private transient ProductionService productionService;

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


	@Override
	public DtoList<Production> getProductions(long jobId, Long first, Long size, String sort, Boolean desc) {

		final LimitParams limitParams = LimitParams.create(first, size);
		final SortParams sortParams = SortParams.create(sort, desc);

		final ResultList<Production> data = productionService.findByJobId(jobId, limitParams, sortParams);

		return new DtoList<>(data, limitParams);
	}

	@Override
	public DtoList<Good> getAddableProductionGoods(long jobId, Long first, Long size, String sort, Boolean desc) {

		final LimitParams limitParams = LimitParams.create(first, size);
		//final SortParams sortParams = SortParams.create(sort, desc);

		// TODO: sorting

		final ResultList<Good> data = jobService.getAddableProductionGoods(jobId);

		return new DtoList<>(data, limitParams);

	}

	@Override
	public Production createProduction(long jobId, Production dto) {
		checkConsistency(jobId, dto);
		return productionService.create(dto);
	}

	@Override
	public void deleteProduction(long jobId, long goodId) {
		productionService.delete(jobId, goodId);
	}

	@Override
	public Production updateProduction(long jobId, Production dto) {
		checkConsistency(jobId, dto);
		return productionService.update(dto);
	}

	private void checkConsistency(final long jobId, final Production dto) {
		if (jobId != dto.getJobId()) {
			throw new IllegalArgumentException("jobId must match the jobId in DTO");
		}
	}

}
