package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.JobEntity;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.JobConversionService;
import com.nigames.jbdd.service.service.AbstractDtoService;
import com.nigames.jbdd.service.service.querystrategy.JobQueryStrategy;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * JobService implementation.
 *
 * @author Daniel
 * @see JobServiceImpl
 */
@Service
public class JobServiceImpl extends AbstractDtoService<Job, JobEntity> implements JobService {

    @Autowired
    private JobConversionService jobConversionService;

    @Autowired
    private JobQueryStrategy jobQueryStrategy;

    @Override
    @Transactional
    public Job create(final Job dto) {
        return super.create(dto);
    }

    @Override
    @Transactional
    public Job update(final long id, final Job dto) {
        return super.update(id, dto);
    }

    @Override
    @Transactional
    public void delete(final long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    public List<Job> findAll(final LimitParams limitParams, final SortParams sortParams) {
        return super.findAll(limitParams, sortParams);
    }

    @Override
    @Transactional
    public List<Job> findAll(final LimitParams limitParams, final SortParams sortParams, final QueryStrategy<JobEntity> queryStrategy,
                             final Object... queryParams) {
        return super.findAll(limitParams, sortParams, queryStrategy, queryParams);
    }

    @Override
    @Transactional
    public Job findById(final long entityId) {
        return super.findById(entityId);
    }

    @Override
    @Transactional
    public long getCount() {
        return super.getCount();
    }

    @Override
    protected Class<JobEntity> getEntityClass() {
        return JobEntity.class;
    }

    @Override
    protected ConversionServiceInterface<JobEntity, Job> getConversionService() {
        return jobConversionService;
    }

    @Override
    protected QueryStrategy<JobEntity> getDefaultQueryStrategy() {
        return jobQueryStrategy;
    }

}
