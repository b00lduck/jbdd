package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedGoodEntity;
import com.nigames.jbdd.rest.dto.PlayerGood;
import com.nigames.jbdd.service.conversion.dto.module.IdConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerResourceConversionService extends
		AbstractConversionService<PlayerAssignedGoodEntity, PlayerGood> {

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
    public PlayerGood getNewDtoInstance(final Class<?> entityClass) {
	    return new PlayerGood();
    }

    @Override
    protected void updateDtoFromEntity(final PlayerGood dto, final PlayerAssignedGoodEntity entity) {
        // nothing to do here.
    }

    @Override
    public void updateEntityFromDto(final PlayerGood dto, final PlayerAssignedGoodEntity entity) {
        // nothing to do here.
    }

}
