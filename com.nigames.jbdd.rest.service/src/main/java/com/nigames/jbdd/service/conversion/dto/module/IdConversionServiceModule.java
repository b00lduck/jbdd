package com.nigames.jbdd.service.conversion.dto.module;

import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacet;
import com.nigames.jbdd.rest.dto.facet.Identifiable;
import com.nigames.jbdd.rest.dto.facet.IsDto;
import org.springframework.stereotype.Component;

@Component
public class IdConversionServiceModule implements ConversionServiceModuleInterface {

	@Override
	public void updateDtoFromEntity(final IsDto dto, final Object entity) {

		// TODO: stronger types in method signature, check types compile time rather than runtime!

		checkTypes(dto, entity);

		((Identifiable) dto).setId(((IdentifyableEntityFacet) entity).getId());

	}

	@Override
	public void updateEntityFromDto(final Object entity, final IsDto dto) {

		// TODO: stronger types in method signature, check types compile time rather than runtime!

		checkTypes(dto, entity);

	}

	private void checkTypes(final IsDto dto, final Object entity) {

		// TODO: stronger types in method signature, check types compile time rather than runtime!

		if (!(entity instanceof IdentifyableEntityFacet)) {
			throw new IllegalArgumentException("entity must be of type IdentifyableEntityFacet");
		}

		if (!(dto instanceof Identifiable)) {
			throw new IllegalArgumentException("dto must be of type Identifiable");
		}
	}

}
