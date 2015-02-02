package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.service.conversion.dto.module.IdEnabledConversionServiceModule;
import com.nigames.jbdd.service.conversion.dto.module.NameDescConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingConversionService extends AbstractConversionService<BuildingEntity, Building> {

    @Autowired
    private NameDescConversionServiceModule nameDescConversionServiceModule;

    @Autowired
    private IdEnabledConversionServiceModule idEnabledConversionServiceModule;

    @Override
    public BuildingEntity getNewEntityInstance() {
        return BuildingEntity.newInstance();
    }

    @Override
    public Building getNewDtoInstance(final Class<?> entityClass) {
        return new Building();
    }

    @Override
    protected void addModules() {
        addModule(nameDescConversionServiceModule);
        addModule(idEnabledConversionServiceModule);
    }

    @Override
    protected void updateDtoFromEntity(final Building dto, final BuildingEntity entity) {
        dto.setDeletable(true);
    }

    @Override
    public void updateEntityFromDto(final Building dto, final BuildingEntity entity) {
        // Nothing to do here (yet).
    }

}
