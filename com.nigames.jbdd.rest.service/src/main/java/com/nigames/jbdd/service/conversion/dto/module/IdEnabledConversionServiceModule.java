package com.nigames.jbdd.service.conversion.dto.module;

import com.nigames.jbdd.rest.dto.aspects.IsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdEnabledConversionServiceModule implements ConversionServiceModuleInterface {

    @Autowired
    private transient IdConversionServiceModule idConversionServiceModule;

    @Autowired
    private transient EnabledConversionServiceModule enabledConversionServiceModule;

    @Override
    public void updateDtoFromEntity(final IsDto dto, final Object entity) {
        idConversionServiceModule.updateDtoFromEntity(dto, entity);
        enabledConversionServiceModule.updateDtoFromEntity(dto, entity);
    }

    @Override
    public void updateEntityFromDto(final Object entity, final IsDto dto) {
        idConversionServiceModule.updateEntityFromDto(entity, dto);
        enabledConversionServiceModule.updateEntityFromDto(entity, dto);
    }

}
