package com.nigames.jbdd.service.conversion.impl.modules;

import com.nigames.jbdd.rest.dto.facet.IsDto;

public interface ConversionServiceTestModuleInterface {

    void fillEntity(final Object entity);

    void checkDto(final IsDto dto);

    void fillDto(final IsDto dto);

    void checkEntity(final Object entity);

}
