package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.item.StoragetypeEntity;
import com.nigames.jbdd.rest.dto.Storagetype;
import com.nigames.jbdd.service.conversion.dto.module.IdEnabledConversionServiceModule;
import com.nigames.jbdd.service.conversion.dto.module.NameDescConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoragetypeConversionService extends
        AbstractConversionService<StoragetypeEntity, Storagetype> {

    @Autowired
    private transient NameDescConversionServiceModule nameDescConversionServiceModule;

    @Autowired
    private transient IdEnabledConversionServiceModule idEnabledConversionServiceModule;

    @Override
    public StoragetypeEntity getNewEntityInstance() {
        return new StoragetypeEntity();
    }

    @Override
    public Storagetype getNewDtoInstance() {
        return new Storagetype();
    }

    @Override
    protected void addModules() {
        addModule(nameDescConversionServiceModule);
        addModule(idEnabledConversionServiceModule);
    }

    @Override
    protected void updateDtoFromEntity(final Storagetype dto, final StoragetypeEntity entity) {
        // Nothing to do here (yet).
    }

    @Override
    protected void updateEntityFromDto(final Storagetype dto, final StoragetypeEntity entity) {
        // Nothing to do here (yet).
    }

}