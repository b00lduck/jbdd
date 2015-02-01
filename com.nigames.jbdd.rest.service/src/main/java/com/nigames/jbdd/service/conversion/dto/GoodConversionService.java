package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.conversion.dto.module.IdEnabledConversionServiceModule;
import com.nigames.jbdd.service.conversion.dto.module.NameDescConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodConversionService extends AbstractConversionService<GoodEntity, Good> {

    @Autowired
    private transient NameDescConversionServiceModule nameDescConversionServiceModule;

    @Autowired
    private transient IdEnabledConversionServiceModule idEnabledConversionServiceModule;

    @Override
    public GoodEntity getNewEntityInstance() {
        return GoodEntity.newInstance();
    }

    @Override
    public Good getNewDtoInstance(Class<?> entityClass) {
        return new Good();
    }

    @Override
    protected void addModules() {
        addModule(nameDescConversionServiceModule);
        addModule(idEnabledConversionServiceModule);
    }

    @Override
    protected void updateDtoFromEntity(final Good dto, final GoodEntity entity) {
        dto.setWeight(entity.getWeight());
        dto.setDeletable(true);
    }

    @Override
    public void updateEntityFromDto(final Good dto, final GoodEntity entity) {
        entity.setWeight(dto.getWeight());
    }

}
