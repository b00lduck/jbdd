package com.nigames.jbdd.service.conversion.dto.module;

import com.nigames.jbdd.domain.entities.aspect.CanBeEnabledEntityAspect;
import com.nigames.jbdd.rest.dto.aspects.CanBeEnabled;
import com.nigames.jbdd.rest.dto.aspects.IsDto;
import org.springframework.stereotype.Component;

@Component
public class EnabledConversionServiceModule implements ConversionServiceModuleInterface {

    @Override
    public void updateDtoFromEntity(final IsDto dto, final Object entity) {

        // TODO: stronger types in method signature

        checkTypes(dto, entity);

        ((CanBeEnabled) dto).setEnabled(((CanBeEnabledEntityAspect) entity).isEnabled());
    }

    @Override
    public void updateEntityFromDto(final Object entity, final IsDto dto) {

        // TODO: stronger types in method signature

        checkTypes(dto, entity);


        ((CanBeEnabledEntityAspect) entity).setEnabled(((CanBeEnabled) dto).isEnabled());
    }

    private void checkTypes(final IsDto dto, final Object entity) {

        // TODO: stronger types in method signature

        if (!(entity instanceof CanBeEnabledEntityAspect)) {
            throw new IllegalArgumentException("entity must be of type CanBeEnabledAspect");
        }

        if (!(dto instanceof CanBeEnabled)) {
            throw new IllegalArgumentException("dto must be of type CanBeEnabled");
        }
    }

}
