package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.item.TechnologyEntity;
import com.nigames.jbdd.rest.dto.Technology;
import com.nigames.jbdd.service.conversion.dto.module.IdEnabledConversionServiceModule;
import com.nigames.jbdd.service.conversion.dto.module.NameDescConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnologyConversionService extends
        AbstractConversionService<TechnologyEntity, Technology> {

    @Autowired
    private transient NameDescConversionServiceModule nameDescConversionServiceModule;

    @Autowired
    private transient IdEnabledConversionServiceModule idEnabledConversionServiceModule;

    @Override
    public TechnologyEntity getNewEntityInstance() {
        return new TechnologyEntity();
    }

    @Override
    public Technology getNewDtoInstance() {
        return new Technology();
    }

    @Override
    protected void addModules() {
        addModule(nameDescConversionServiceModule);
        addModule(idEnabledConversionServiceModule);
    }

    @Override
    protected void updateDtoFromEntity(final Technology dto, final TechnologyEntity entity) {
        // Nothing to do here (yet).
    }

    @Override
    public void updateEntityFromDto(final Technology dto, final TechnologyEntity entity) {
        // Nothing to do here (yet).
    }

}