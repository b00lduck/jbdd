package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.JobEntity;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.rest.dto.Production;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.JobConversionService;
import com.nigames.jbdd.service.repository.JobRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.service.service.sortParamTransformator.NameSortParamTransformator;
import com.nigames.jbdd.service.service.subitem.ProductionService;
import com.nigames.jbdd.types.ResultList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JobService implementation.
 *
 * @author Daniel
 * @see JobServiceImpl
 */
@Service
public class JobServiceImpl extends AbstractRepositoryBackedService<JobEntity, Long, Job>
		implements JobService, InitializingBean {

	@Autowired
	private JobConversionService jobConversionService;

	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private GoodService goodService;

	@Autowired
	private ProductionService productionService;

	@Override
	public void afterPropertiesSet() throws Exception {
		addSortParamTransformator(new NameSortParamTransformator());
	}

	@Override
	protected JobRepository getRepository() {
		return jobRepository;
	}

	@Override
	protected ConversionServiceInterface<JobEntity, Job> getConversionService() {
		return jobConversionService;
	}

	@Override
	public ResultList<Good> getAddableProductionGoods(long jobId) {

		final List<Good> goodList = goodService.findAllEnabled();

		final List<Good> ret = new ArrayList<>();

		final Set<Long> productionGoodList = getProductionsForJob(jobId);

		for (final Good g : goodList) {

			// Cannot add already added productions
			if (productionGoodList.contains(g.getId())) {
				continue;
			}

			ret.add(g);

		}

		return ResultList.create(ret);
	}


	private Set<Long> getProductionsForJob(final long jobId) {

		final Set<Long> ret = new HashSet<>();

		final ResultList<Production> reqList = productionService.findByJobId(jobId);

		// TODO think about syntax vs. performance
		ret.addAll(reqList.stream().map(Production::getGoodId).collect(Collectors.toList()));

		return ret;
	}


	@Override
	public List<Job> findAllEnabled() {
		final List<JobEntity> entityList = jobRepository.findByEnabled(true);
		return jobConversionService.convertToDto(entityList);
	}


}
