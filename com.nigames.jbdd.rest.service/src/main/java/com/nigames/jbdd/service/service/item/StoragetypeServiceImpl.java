package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.StoragetypeEntity;
import com.nigames.jbdd.rest.dto.Storagetype;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.StoragetypeConversionService;
import com.nigames.jbdd.service.repository.StoragetypeRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StoragetypeService implementation.
 *
 * @author Daniel
 * @see StoragetypeServiceImpl
 */
@Service
public class StoragetypeServiceImpl extends AbstractRepositoryBackedService<StoragetypeEntity, Long, Storagetype>
		implements StoragetypeService {

	@Autowired
	private StoragetypeConversionService buildingConversionService;

	@Autowired
	private StoragetypeRepository buildingRepository;

	@Override
	protected StoragetypeRepository getRepository() {
		return buildingRepository;
	}

	@Override
	protected ConversionServiceInterface<StoragetypeEntity, Storagetype> getConversionService() {
		return buildingConversionService;
	}

}
