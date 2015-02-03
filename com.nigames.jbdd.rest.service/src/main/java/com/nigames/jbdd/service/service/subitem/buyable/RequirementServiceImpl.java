package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntityPK;
import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.RequirementConversionService;
import com.nigames.jbdd.service.repository.RequirementRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * RequirementService implementation.
 *
 * @author Daniel
 */
@Service
public class RequirementServiceImpl extends AbstractRepositoryBackedService<RequirementEntity, RequirementEntityPK,
		Requirement> implements RequirementService {

	private static final Logger LOG = LoggerFactory.getLogger(RequirementServiceImpl.class);

	@Autowired
	private RequirementConversionService requirementConversionService;

	@Autowired
	private RequirementRepository requirementRepository;

	@Override
	protected RequirementRepository getRepository() {
		return requirementRepository;
	}

	@Override
	protected ConversionServiceInterface<RequirementEntity, Requirement> getConversionService() {
		return requirementConversionService;
	}

	@Override
	@Transactional
	public Requirement update(final Requirement dto) {
		final RequirementEntityPK id = new RequirementEntityPK(dto.getBuyableId(), dto.getRequiredBuyableId());
		return update(id, dto);
	}

	@Override
	@Transactional
	public void delete(final long buyableId, final long requiredBuyableId) {
		final RequirementEntityPK id = new RequirementEntityPK(buyableId, requiredBuyableId);
		delete(id);
	}

	@Override
	public List<Requirement> findByBuyableId(final long buyableId) {
		List<RequirementEntity> page = requirementRepository.findByIdBuyableId(buyableId);
		return requirementConversionService.convertToDto(page);
	}

	@Override
	public List<Requirement> findByBuyableId(final long buyableId, final LimitParams limitParams,
	                                         final SortParams sortParams) {
		Page<RequirementEntity> page =
				requirementRepository.findByIdBuyableId(buyableId, createPageable(limitParams, sortParams));
		return requirementConversionService.convertToDto(page.getContent());
	}

	@Override
	public long getCountByBuyableId(final long buyableId) {
		return requirementRepository.countByIdBuyableId(buyableId);
	}

}
