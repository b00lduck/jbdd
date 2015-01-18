package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.subitem.playerSubItem.PlayerAssignedBuildingEntity;
import com.nigames.jbdd.rest.dto.PlayerBuilding;
import org.springframework.stereotype.Service;

@Service
public class PlayerBuildingConversionService extends
        AbstractConversionService<PlayerAssignedBuildingEntity, PlayerBuilding> {

    @Override
    public PlayerAssignedBuildingEntity getNewEntityInstance() {
        return new PlayerAssignedBuildingEntity();
    }

    @Override
    public PlayerBuilding getNewDtoInstance() {
        return new PlayerBuilding();
    }

    @Override
    protected void addModules() {
        // Nothing to do here.
    }

    @Override
    protected void updateDtoFromEntity(final PlayerBuilding dto, final PlayerAssignedBuildingEntity entity) {
        dto.setId(entity.getId());
    }

    @Override
    public void updateEntityFromDto(final PlayerBuilding dto, final PlayerAssignedBuildingEntity entity) {
        // Nothing to do here.
    }

}
