package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.conversion.dto.module.ConversionServiceModuleInterface;
import org.springframework.beans.factory.InitializingBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConversionService<EntityType, DtoType extends IsDto>
		implements ConversionServiceInterface<EntityType, DtoType>, InitializingBean {

	private final Collection<ConversionServiceModuleInterface> moduleList =
			new ArrayList<>();

	@Override
	public List<DtoType> convertToDto(final List<EntityType> entityList) {
		return entityList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

	@Override
	public List<EntityType> convertToEntity(final List<DtoType> dtoList) {
		return dtoList.stream().map(this::convertToEntity).collect(Collectors.toList());
	}

	@Override
	public EntityType convertToEntity(final DtoType dto) {
		final EntityType entity = getNewEntityInstance();
		updateEntity(dto, entity);
		return entity;
	}

	@Override
	public DtoType convertToDto(final EntityType entity) {
		final DtoType dto = getNewDtoInstance(entity.getClass());
		updateDto(entity, dto);
		return dto;
	}

	@Override
	public void updateDto(final EntityType entity, final DtoType dto) {
		for (final ConversionServiceModuleInterface module : moduleList) {
			module.updateDtoFromEntity(dto, entity);
		}
		updateDtoFromEntity(dto, entity);
	}

	@Override
	public void updateEntity(final DtoType dto, final EntityType entity) {
		for (final ConversionServiceModuleInterface module : moduleList) {
			module.updateEntityFromDto(entity, dto);
		}
		updateEntityFromDto(dto, entity);
	}

	@Override
	public void afterPropertiesSet() {
		addModules();
	}

	protected abstract void addModules();

	void addModule(final ConversionServiceModuleInterface module) {
		moduleList.add(module);
	}

	protected abstract void updateEntityFromDto(final DtoType dto, final EntityType entity);

	protected abstract void updateDtoFromEntity(final DtoType dto, final EntityType entity);

	public abstract EntityType getNewEntityInstance();

	public abstract DtoType getNewDtoInstance(Class<?> entityClass);

}
