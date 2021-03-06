package com.nigames.jbdd.service.service.subitem.buyable;

import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntityPK;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.CostConversionService;
import com.nigames.jbdd.service.repository.CostRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CostService implementation.
 *
 * @author Daniel
 * @see CostServiceImpl
 */
@Service
public class CostServiceImpl extends AbstractRepositoryBackedService<CostEntity, CostEntityPK, Cost> implements CostService {

	private static final Logger LOG = LoggerFactory.getLogger(CostServiceImpl.class);

	@Autowired
	private CostConversionService costConversionService;

	@Autowired
	private CostRepository costRepository;

	@Override
	protected CostRepository getRepository() {
		return costRepository;
	}

	@Override
	protected ConversionServiceInterface<CostEntity, Cost> getConversionService() {
		return costConversionService;
	}

	@Override
	@Transactional
	public Cost update(final Cost dto) {
		final CostEntityPK id = new CostEntityPK(dto.getBuyableId(), dto.getGoodId());
		return update(id, dto);
	}

	@Override
	@Transactional
	public void delete(final long buyableId, final long goodId) {
		final CostEntityPK id = new CostEntityPK(buyableId, goodId);
		delete(id);
	}

	@Override
	public ResultList<Cost> findByBuyableId(final long buyableId, final LimitParams limitParams, final SortParams sortParams) {
		final Pageable pageable = createPageable(limitParams, sortParams);

		final List<CostEntity> costEntityList = costRepository.findByIdBuyableId(buyableId, pageable).getContent();
        final List<Cost> list = costConversionService.convertToDto(costEntityList);

		return ResultList.create(list, costRepository.countByIdBuyableId(buyableId));
	}

	@Override
	public ResultList<Cost> findByBuyableId(final long buyableId) {
		final List<CostEntity> costEntityList = costRepository.findByIdBuyableId(buyableId);

        final List<Cost> list = costConversionService.convertToDto(costEntityList);

        return ResultList.create(list);
	}

}
