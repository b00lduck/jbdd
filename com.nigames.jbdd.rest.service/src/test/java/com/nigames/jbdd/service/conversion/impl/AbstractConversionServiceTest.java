package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.conversion.dto.AbstractConversionService;
import com.nigames.jbdd.service.conversion.impl.modules.ConversionServiceTestModuleInterface;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Test for the *ConversionService classes.
 *
 * @author Daniel
 */
public abstract class AbstractConversionServiceTest<DtoType extends IsDto, EntityType> {

	protected static final String TEST_SMALLIMAGE_PATH = "TestSmallImagePath";

	private final List<ConversionServiceTestModuleInterface> moduleList = new ArrayList<ConversionServiceTestModuleInterface>();

	public AbstractConversionServiceTest() {
		addModules();
	}

	protected abstract AbstractConversionService<EntityType, DtoType> getConversionService();

	protected abstract void fillEntity(final EntityType entity);

	protected abstract void checkDto(final DtoType dto);

	protected abstract void fillDto(final DtoType dto);

	protected abstract void checkEntity(final EntityType entity);

	void addModule(final ConversionServiceTestModuleInterface module) {
		moduleList.add(module);
	}

	protected abstract void addModules();

	@Test
	public void entityToDtoConversionTest() {
		final EntityType entity = getConversionService().getNewEntityInstance();

		for (final ConversionServiceTestModuleInterface module : moduleList) {
			module.fillEntity(entity);
		}

		fillEntity(entity);
		final DtoType dto = getConversionService().convertToDto(entity);

		for (final ConversionServiceTestModuleInterface module : moduleList) {
			module.checkDto(dto);
		}
		checkDto(dto);
	}

	@Test
	@Ignore
	public void dtoToEntityConversionTest() {
		DtoType dto = getConversionService().getNewDtoInstance(null);
		for (ConversionServiceTestModuleInterface module : moduleList) {
			module.fillDto(dto);
		}
		fillDto(dto);
		EntityType entity = getConversionService().convertToEntity(dto);
		for (ConversionServiceTestModuleInterface module : moduleList) {
			module.checkEntity(entity);
		}
		checkEntity(entity);
	}
}
