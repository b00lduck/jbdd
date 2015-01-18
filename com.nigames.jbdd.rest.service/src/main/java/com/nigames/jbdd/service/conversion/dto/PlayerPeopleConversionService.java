package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedPeopleEntity;
import com.nigames.jbdd.rest.dto.PlayerPeople;
import com.nigames.jbdd.service.conversion.dto.module.IdConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerPeopleConversionService extends
        AbstractConversionService<PlayerAssignedPeopleEntity, PlayerPeople> {

    @Autowired
    private transient IdConversionServiceModule idConversionServiceModule;

    @Override
    protected void addModules() {
        addModule(idConversionServiceModule);
    }

    @Override
    public PlayerAssignedPeopleEntity getNewEntityInstance() {
        return new PlayerAssignedPeopleEntity();
    }

    @Override
    public PlayerPeople getNewDtoInstance() {
        return new PlayerPeople();
    }

    @Override
    protected void updateDtoFromEntity(final PlayerPeople dto, final PlayerAssignedPeopleEntity entity) {
        // nothing to do here.
    }

    @Override
    public void updateEntityFromDto(final PlayerPeople dto, final PlayerAssignedPeopleEntity entity) {
        // nothing to do here.
    }

}
