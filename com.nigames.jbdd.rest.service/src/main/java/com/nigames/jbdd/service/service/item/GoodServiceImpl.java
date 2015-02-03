package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.GoodConversionService;
import com.nigames.jbdd.service.repository.GoodRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GoodService implementation.
 *
 * @author Daniel
 * @see GoodServiceImpl
 */
@Service
public class GoodServiceImpl extends AbstractRepositoryBackedService<GoodEntity, Long, Good>
		implements GoodService {

	@Autowired
	private GoodConversionService buildingConversionService;

	@Autowired
	private GoodRepository buildingRepository;

	@Override
	protected GoodRepository getRepository() {
		return buildingRepository;
	}

	@Override
	protected ConversionServiceInterface<GoodEntity, Good> getConversionService() {
		return buildingConversionService;
	}

}
