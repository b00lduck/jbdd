package com.nigames.jbdd.service.conversion.dto.module;

import com.nigames.jbdd.domain.entities.aspect.identifyable.IdentifyableEntityAspect;
import com.nigames.jbdd.rest.dto.aspects.Identifiable;
import com.nigames.jbdd.rest.dto.aspects.IsDto;
import org.springframework.stereotype.Component;

@Component
public class IdConversionServiceModule implements ConversionServiceModuleInterface {

	@Override
	public void updateDtoFromEntity(final IsDto dto, final Object entity) {

		// TODO: stronger types in method signature

		checkTypes(dto, entity);

		((Identifiable) dto).setId(((IdentifyableEntityAspect) entity).getId());

	}

	@Override
	public void updateEntityFromDto(final Object entity, final IsDto dto) {

		// TODO: stronger types in method signature

		checkTypes(dto, entity);

	}

	private void checkTypes(final IsDto dto, final Object entity) {

		// TODO: stronger types in method signature

		if (!(entity instanceof IdentifyableEntityAspect)) {
			throw new IllegalArgumentException("entity must be of type IdentifyableEntityAspect");
		}

		if (!(dto instanceof Identifiable)) {
			throw new IllegalArgumentException("dto must be of type Identifiable");
		}
	}

}
