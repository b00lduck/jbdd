package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntityPK;
import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.RequirementConversionService;
import com.nigames.jbdd.service.service.AbstractDtoService;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.service.service.querystrategy.RequirementQueryStrategy;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
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
 * @see com.nigames.jbdd.service.service.subitem.buyable.RequirementServiceImpl
 */
@Service
public class RequirementServiceImpl extends AbstractDtoService<Requirement, RequirementEntity> implements RequirementService {

	private static final Logger LOG = LoggerFactory.getLogger(RequirementServiceImpl.class);

	@Autowired
	private RequirementConversionService requirementConversionService;

	@Autowired
	private RequirementQueryStrategy requirementQueryStrategy;

	@Override
	@Transactional
	public Requirement create(final Requirement dto) {
		return super.create(dto);
	}

	@Override
	@Transactional
	public Requirement update(final Requirement dto) {
		final RequirementEntityPK id = new RequirementEntityPK(dto.getBuyableId(), dto.getRequiredBuyableId());
		final RequirementEntity entity = getEntityManager().find(getEntityClass(), id);
		getConversionService().updateEntity(dto, entity);
		return getConversionService().convertToDto(entity);
	}

	@Override
	@Transactional
	public void delete(final long buyableId, final long requiredBuyableId) {
		final RequirementEntityPK id = new RequirementEntityPK(buyableId, requiredBuyableId);
		final RequirementEntity entity = getEntityManager().getReference(getEntityClass(), id);
		getEntityManager().remove(entity);
	}

	@Override
	@Transactional
	public List<Requirement> findAll(final LimitParams limitParams, final SortParams sortParams) {
		return super.findAll(limitParams, sortParams);
	}

	@Override
	public List<Requirement> findByBuyableId(final long buyableId) {
		return findByBuyableId(buyableId, LimitParams.createDefault(), SortParams.createDefault());
	}

	@Override
	public List<Requirement> findByBuyableId(final long buyableId, final LimitParams limitParams, final SortParams sortParams) {

		final TypedQuery<RequirementEntity> query =
				getEntityManager().createNamedQuery(RequirementEntity.NQ_BY_BUYABLE_ID, RequirementEntity.class);

		query.setParameter("buyableId", buyableId);

		final List<RequirementEntity> requirementEntityList = query.getResultList();

		return requirementConversionService.convertToDto(requirementEntityList);
	}

	@Override
	public long getCountByBuyableId(long buyableId) {
		final TypedQuery<Long> query =
				getEntityManager().createNamedQuery(RequirementEntity.NQ_COUNT_BY_BUYABLE_ID, Long.class);
		query.setParameter("buyableId", buyableId);
		return query.getSingleResult();
	}

	@Override
	@Transactional
	public List<Requirement> findAll(final LimitParams limitParams, final SortParams sortParams,
	                                 final QueryStrategy<RequirementEntity> queryStrategy, final Object... queryParams) {
		return super.findAll(limitParams, sortParams, queryStrategy, queryParams);
	}

	@Override
	@Transactional
	public Requirement findById(final long entityId) {
		return super.findById(entityId);
	}

	@Override
	@Transactional
	public long getCount() {
		return super.getCount();
	}

	@Override
	protected Class<RequirementEntity> getEntityClass() {
		return RequirementEntity.class;
	}

	@Override
	protected ConversionServiceInterface<RequirementEntity, Requirement> getConversionService() {
		return requirementConversionService;
	}

	@Override
	protected QueryStrategy<RequirementEntity> getDefaultQueryStrategy() {
		return requirementQueryStrategy;
	}

}
