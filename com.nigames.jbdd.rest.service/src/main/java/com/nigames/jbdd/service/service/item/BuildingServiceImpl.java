package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.service.conversion.dto.BuildingConversionService;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.repository.BuildingRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.service.service.sortParamTransformator.NameSortParamTransformator;
import com.nigames.jbdd.service.service.subitem.ProductionService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * BuildingService implementation.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.item.BuildingServiceImpl
 */
@Service
public class BuildingServiceImpl extends AbstractRepositoryBackedService<BuildingEntity, Long, Building>
		implements BuildingService, InitializingBean {

	@Autowired
	private BuildingConversionService buildingConversionService;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private ProductionService productionService;

	@Autowired
	private GoodService goodService;

	@Override
	public void afterPropertiesSet() throws Exception {
		addSortParamTransformator(new NameSortParamTransformator());
	}

	@Override
	protected BuildingRepository getRepository() {
		return buildingRepository;
	}

	@Override
	protected ConversionServiceInterface<BuildingEntity, Building> getConversionService() {
		return buildingConversionService;
	}

	@Override
	public List<Building> findAllEnabled() {
		final List<BuildingEntity> entityList = buildingRepository.findByEnabled(true);
		return buildingConversionService.convertToDto(entityList);
	}

}
