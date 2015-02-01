package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedResourceEntity;
import com.nigames.jbdd.rest.dto.PlayerResource;
import com.nigames.jbdd.service.conversion.dto.module.IdConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerResourceConversionService extends
        AbstractConversionService<PlayerAssignedResourceEntity, PlayerResource> {

    @Autowired
    private transient IdConversionServiceModule idConversionServiceModule;

    @Override
    protected void addModules() {
        addModule(idConversionServiceModule);
    }

    @Override
    public PlayerAssignedResourceEntity getNewEntityInstance() {
        return new PlayerAssignedResourceEntity();
    }

    @Override
    public PlayerResource getNewDtoInstance(Class<?> entityClass) {
        return new PlayerResource();
    }

    @Override
    protected void updateDtoFromEntity(final PlayerResource dto, final PlayerAssignedResourceEntity entity) {
        // nothing to do here.
    }

    @Override
    public void updateEntityFromDto(final PlayerResource dto, final PlayerAssignedResourceEntity entity) {
        // nothing to do here.
    }

}
