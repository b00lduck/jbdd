package com.nigames.jbdd.service.conversion.dto.module;

import com.nigames.jbdd.rest.dto.aspects.IsDto;

public interface ConversionServiceModuleInterface {

    void updateDtoFromEntity(final IsDto dto, final Object entity);

    void updateEntityFromDto(final Object entity, final IsDto dto);

}
