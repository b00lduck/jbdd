package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.TechnologyEntity;
import com.nigames.jbdd.rest.dto.Technology;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.TechnologyConversionService;
import com.nigames.jbdd.service.repository.TechnologyRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.service.service.sortParamTransformator.NameSortParamTransformator;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TechnologyService implementation.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.item.TechnologyServiceImpl
 */
@Service
public class TechnologyServiceImpl extends AbstractRepositoryBackedService<TechnologyEntity, Long, Technology>
		implements TechnologyService, InitializingBean {

	@Autowired
	private TechnologyConversionService technologyConversionService;

	@Autowired
	private TechnologyRepository technologyRepository;

	@Override
	public void afterPropertiesSet() throws Exception {
		addSortParamTransformator(new NameSortParamTransformator());
	}

	@Override
	protected TechnologyRepository getRepository() {
		return technologyRepository;
	}

	@Override
	protected ConversionServiceInterface<TechnologyEntity, Technology> getConversionService() {
		return technologyConversionService;
	}

	@Override
	public List<Technology> findAllEnabled() {
		final List<TechnologyEntity> entityList = technologyRepository.findByEnabled(true);
		return technologyConversionService.convertToDto(entityList);
	}
}
