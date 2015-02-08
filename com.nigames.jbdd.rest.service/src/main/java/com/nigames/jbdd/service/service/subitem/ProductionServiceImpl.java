package com.nigames.jbdd.service.service.subitem;

import com.nigames.jbdd.domain.entities.subitem.ProductionEntity;
import com.nigames.jbdd.domain.entities.subitem.ProductionEntityPK;
import com.nigames.jbdd.rest.dto.Production;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.ProductionConversionService;
import com.nigames.jbdd.service.repository.ProductionRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.ResultList;
import com.nigames.jbdd.types.SortParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ProductionService implementation.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.subitem.ProductionServiceImpl
 */
@Service
public class ProductionServiceImpl extends AbstractRepositoryBackedService<ProductionEntity, ProductionEntityPK, Production> implements ProductionService {

	private static final Logger LOG = LoggerFactory.getLogger(ProductionServiceImpl.class);

	@Autowired
	private ProductionConversionService productionConversionService;

	@Autowired
	private ProductionRepository productionRepository;

	@Override
	protected ProductionRepository getRepository() {
		return productionRepository;
	}

	@Override
	protected ConversionServiceInterface<ProductionEntity, Production> getConversionService() {
		return productionConversionService;
	}

	@Override
	@Transactional
	public Production update(final Production dto) {
		final ProductionEntityPK id = new ProductionEntityPK(dto.getBuildingId(), dto.getGoodId());
		return update(id, dto);
	}

	@Override
	@Transactional
	public void delete(final long buildingId, final long goodId) {
		final ProductionEntityPK id = new ProductionEntityPK(buildingId, goodId);
		delete(id);
	}

	@Override
	public ResultList<Production> findByBuildingId(final long buildingId, final LimitParams limitParams, final SortParams sortParams) {
		final Pageable pageable = createPageable(limitParams, sortParams);

		final List<ProductionEntity> productionEntityList = productionRepository.findByIdBuildingId(buildingId, pageable).getContent();
		final List<Production> list = productionConversionService.convertToDto(productionEntityList);

		return ResultList.create(list, productionRepository.countByIdBuildingId(buildingId));
	}

	@Override
	public ResultList<Production> findByBuildingId(final long buildingId) {
		final List<ProductionEntity> productionEntityList = productionRepository.findByIdBuildingId(buildingId);

		final List<Production> list = productionConversionService.convertToDto(productionEntityList);

		return ResultList.create(list);
	}

}
