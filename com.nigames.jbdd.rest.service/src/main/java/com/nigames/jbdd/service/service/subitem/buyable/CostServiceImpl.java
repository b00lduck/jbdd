package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.CostConversionService;
import com.nigames.jbdd.service.service.AbstractDtoService;
import com.nigames.jbdd.service.service.querystrategy.CostQueryStrategy;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * CostService implementation.
 *
 * @author Daniel
 * @see CostServiceImpl
 */
@Service
public class CostServiceImpl extends AbstractDtoService<Cost, CostEntity> implements CostService {

	private static final Logger LOG = LoggerFactory
			.getLogger(CostServiceImpl.class);

	@Autowired
	private transient CostConversionService costConversionService;

	@Autowired
	private transient CostQueryStrategy costQueryStrategy;

	@Override
	@Transactional
	public Cost create(final Cost dto) {
		return super.create(dto);
	}

	@Override
	@Transactional
	public Cost update(final long id, final Cost dto) {
		return super.update(id, dto);
	}

	@Override
	@Transactional
	public void delete(final long id) {
		super.delete(id);
	}

	@Override
	@Transactional
	public List<Cost> findAll(final LimitParams limitParams, final SortParams sortParams) {
		return super.findAll(limitParams, sortParams);
	}

	@Override
	public List<Cost> findByBuyableId(long buyableId, LimitParams limitParams, SortParams sortParams) {

		final TypedQuery<CostEntity> query =
				getEntityManager().createNamedQuery(CostEntity.NQ_BY_BUYABLE_ID, CostEntity.class);

		query.setParameter("buyableId", buyableId);

		final List<CostEntity> costEntityList = query.getResultList();

		return costConversionService.convertToDto(costEntityList);
	}

	@Override
	public long getCountByBuyableId(long buyableId) {
		final TypedQuery<Long> query =
				getEntityManager().createNamedQuery(CostEntity.NQ_COUNT_BY_BUYABLE_ID, Long.class);
		query.setParameter("buyableId", buyableId);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public List<Cost> findAll(final LimitParams limitParams, final SortParams sortParams,
	                          final QueryStrategy<CostEntity> queryStrategy, final Object... queryParams) {
		return super.findAll(limitParams, sortParams, queryStrategy, queryParams);
	}

	@Override
	@Transactional
	public Cost findById(final long entityId) {
		return super.findById(entityId);
	}

	@Override
	@Transactional
	public long getCount() {
		return super.getCount();
	}

	@Override
	protected Class<CostEntity> getEntityClass() {
		return CostEntity.class;
	}

	@Override
	protected ConversionServiceInterface<CostEntity, Cost> getConversionService() {
		return costConversionService;
	}

	@Override
	protected QueryStrategy<CostEntity> getDefaultQueryStrategy() {
		return costQueryStrategy;
	}

}
