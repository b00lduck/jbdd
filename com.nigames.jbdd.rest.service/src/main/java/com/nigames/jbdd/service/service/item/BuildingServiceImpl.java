package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.service.conversion.dto.BuildingConversionService;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.repository.BuildingRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BuildingService implementation.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.item.BuildingServiceImpl
 */
@Service
public class BuildingServiceImpl extends AbstractRepositoryBackedService<BuildingEntity, Long, Building>
		implements BuildingService {

    @Autowired
    private BuildingConversionService buildingConversionService;

	@Autowired
	private BuildingRepository buildingRepository;

	@Override
	protected BuildingRepository getRepository() {
		return buildingRepository;
	}

	@Override
	protected ConversionServiceInterface<BuildingEntity, Building> getConversionService() {
		return buildingConversionService;
	}

}
