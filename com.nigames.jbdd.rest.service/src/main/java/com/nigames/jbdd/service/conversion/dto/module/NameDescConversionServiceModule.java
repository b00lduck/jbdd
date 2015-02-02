package com.nigames.jbdd.service.conversion.dto.module;

import com.nigames.jbdd.domain.entities.facet.HasNameAndDescEntityFacet;
import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity;
import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity;
import com.nigames.jbdd.rest.dto.facet.HasNameAndDesc;
import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.conversion.i18n.AbstractI18nConversionService;
import com.nigames.jbdd.service.conversion.i18n.I18nLongConversionService;
import com.nigames.jbdd.service.conversion.i18n.I18nShortConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class NameDescConversionServiceModule implements ConversionServiceModuleInterface {

    @Autowired
    private I18nShortConversionService i18nShortConversionService;

    @Autowired
    private I18nLongConversionService i18nLongConversionService;

    @Override
    public void updateDtoFromEntity(final IsDto dto, final Object entity) {

        // TODO: stronger types in method signature

        checkTypes(entity, dto);

        final HasNameAndDescEntityFacet nameAndDesc = (HasNameAndDescEntityFacet) entity;
        final Map<String, String> nameDto = AbstractI18nConversionService.convertToDto(nameAndDesc.getName());
        ((HasNameAndDesc) dto).setName(nameDto);

        final Map<String, String> descDto = AbstractI18nConversionService
                .convertToDto(((HasNameAndDescEntityFacet) entity).getDescription());
        ((HasNameAndDesc) dto).setDescription(descDto);
    }

    @Override
    public void updateEntityFromDto(final Object entity, final IsDto dto) {

        // TODO: stronger types in method signature

        checkTypes(entity, dto);

        final HasNameAndDesc castedDto = (HasNameAndDesc) dto;
        final HasNameAndDescEntityFacet castedEntity = (HasNameAndDescEntityFacet) entity;

        if (!(castedEntity.getName() instanceof I18nShortEntity)) {
            throw new IllegalArgumentException("name must be of type I18nShortEntity");
        }

        i18nShortConversionService.updateEntityFromDto(castedDto.getName(), (I18nShortEntity) castedEntity.getName());

        if (!(castedEntity.getDescription() instanceof I18nLongEntity)) {
            throw new IllegalArgumentException("description must be of type I18nLongEntity");
        }

        i18nLongConversionService.updateEntityFromDto(castedDto.getDescription(), (I18nLongEntity) castedEntity.getDescription());

    }

    private void checkTypes(final Object entity, final IsDto dto) {

        // TODO: stronger types in method signature

        if (!(entity instanceof HasNameAndDescEntityFacet)) {
            throw new IllegalArgumentException("entity must be of type HasNameAndDescEntityAspect");
        }

        if (!(dto instanceof HasNameAndDesc)) {
            throw new IllegalArgumentException("dto must be of type HasNameAndDesc");
        }
    }

}
