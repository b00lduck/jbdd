package com.nigames.jbdd.service.conversion.impl.modules;

import com.nigames.jbdd.rest.dto.aspects.IsDto;

public class ImageConversionServiceTestModule implements ConversionServiceTestModuleInterface {

    protected static final Boolean TEST_ENABLED = Boolean.TRUE;
    protected static final Long TEST_ID = 42L;

    @Override
    public void fillEntity(final Object entity) {
    }

    @Override
    public void checkDto(final IsDto dto) {
    }

    @Override
    public void fillDto(final IsDto dto) {
    }

    @Override
    public void checkEntity(final Object entity) {
    }

}
