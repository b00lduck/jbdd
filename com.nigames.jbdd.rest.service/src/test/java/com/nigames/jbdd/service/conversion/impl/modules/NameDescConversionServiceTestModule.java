package com.nigames.jbdd.service.conversion.impl.modules;

import com.nigames.jbdd.domain.entities.aspect.HasNameAndDescEntityAspect;
import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity;
import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity;
import com.nigames.jbdd.rest.dto.aspects.HasNameAndDesc;
import com.nigames.jbdd.rest.dto.aspects.IsDto;
import com.nigames.jbdd.service.conversion.AbstractI18nConversionServiceTest;
import org.springframework.util.Assert;

public class NameDescConversionServiceTestModule implements ConversionServiceTestModuleInterface {

    private static final String TEST_NAME = "TestName";
    private static final String TEST_DESC = "TestDesc";

    @Override
    public void fillEntity(final Object entity) {

        final HasNameAndDescEntityAspect castedEntity = getCastedEntity(entity);

        final I18nShortEntity name = new I18nShortEntity();
        AbstractI18nConversionServiceTest.fillI18nEntity(name, TEST_NAME);
        castedEntity.setName(name);

        final I18nLongEntity description = new I18nLongEntity();
        AbstractI18nConversionServiceTest.fillI18nEntity(description, TEST_DESC);
        castedEntity.setDescription(description);
    }

    @Override
    public void checkDto(final IsDto dto) {
        final HasNameAndDesc castedDto = getCastedDto(dto);
        AbstractI18nConversionServiceTest.checkI18nDto(castedDto.getName(), TEST_NAME);
        AbstractI18nConversionServiceTest.checkI18nDto(castedDto.getDescription(), TEST_DESC);
    }

    @Override
    public void fillDto(final IsDto dto) {
        final HasNameAndDesc castedDto = getCastedDto(dto);
        castedDto.setName(AbstractI18nConversionServiceTest.getFilledI18nDto(TEST_NAME));
        castedDto.setDescription(AbstractI18nConversionServiceTest.getFilledI18nDto(TEST_DESC));
    }

    @Override
    public void checkEntity(final Object entity) {
        final HasNameAndDescEntityAspect castedEntity = getCastedEntity(entity);
        AbstractI18nConversionServiceTest.checkI18nEntity(castedEntity.getName(), TEST_NAME);
        AbstractI18nConversionServiceTest.checkI18nEntity(castedEntity.getDescription(), TEST_DESC);
    }

    private HasNameAndDescEntityAspect getCastedEntity(Object entity) {
        Assert.notNull(entity, "entity must not be null");
        Assert.isInstanceOf(HasNameAndDescEntityAspect.class, entity, "entity must be of type HasNameAndDescEntityAspect");
        return (HasNameAndDescEntityAspect) entity;
    }

    private HasNameAndDesc getCastedDto(IsDto dto) {
        Assert.notNull(dto, "dto must not be null");
        Assert.isInstanceOf(HasNameAndDesc.class, dto, "dto must be of type HasNameAndDesc");
        return (HasNameAndDesc) dto;
    }

}
