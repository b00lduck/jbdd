package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedTechnologyEntity;
import com.nigames.jbdd.rest.dto.PlayerTechnology;
import com.nigames.jbdd.service.conversion.dto.module.IdConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerTechnologyConversionService extends
        AbstractConversionService<PlayerAssignedTechnologyEntity, PlayerTechnology> {

    @Autowired
    private transient IdConversionServiceModule idConversionServiceModule;

    @Override
    protected void addModules() {
        addModule(idConversionServiceModule);
    }

    @Override
    public PlayerAssignedTechnologyEntity getNewEntityInstance() {
        return new PlayerAssignedTechnologyEntity();
    }

    @Override
    public PlayerTechnology getNewDtoInstance() {
        return new PlayerTechnology();
    }

    @Override
    protected void updateDtoFromEntity(final PlayerTechnology dto,
                                       final PlayerAssignedTechnologyEntity entity) {
        // nothing to do here.
    }

    @Override
    public void updateEntityFromDto(final PlayerTechnology dto, final PlayerAssignedTechnologyEntity entity) {
        // nothing to do here.
    }

}
