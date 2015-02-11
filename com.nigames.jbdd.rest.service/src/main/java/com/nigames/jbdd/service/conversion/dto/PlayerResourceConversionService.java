package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedGoodEntity;
import com.nigames.jbdd.rest.dto.PlayerResource;
import com.nigames.jbdd.service.conversion.dto.module.IdConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerResourceConversionService extends
        AbstractConversionService<PlayerAssignedGoodEntity, PlayerResource> {

    @Autowired
    private IdConversionServiceModule idConversionServiceModule;

    @Override
    protected void addModules() {
        addModule(idConversionServiceModule);
    }

    @Override
    public PlayerAssignedGoodEntity getNewEntityInstance() {
        return new PlayerAssignedGoodEntity();
    }

    @Override
    public PlayerResource getNewDtoInstance(final Class<?> entityClass) {
        return new PlayerResource();
    }

    @Override
    protected void updateDtoFromEntity(final PlayerResource dto, final PlayerAssignedGoodEntity entity) {
        // nothing to do here.
    }

    @Override
    public void updateEntityFromDto(final PlayerResource dto, final PlayerAssignedGoodEntity entity) {
        // nothing to do here.
    }

}
