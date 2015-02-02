package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.service.conversion.dto.module.IdEnabledConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerConversionService extends AbstractConversionService<PlayerEntity, Player> {

    @Autowired
    private IdEnabledConversionServiceModule idEnabledConversionServiceModule;

    @Override
    public PlayerEntity getNewEntityInstance() {
        return new PlayerEntity();
    }

    @Override
    public Player getNewDtoInstance(final Class<?> entityClass) {
        return new Player();
    }

    @Override
    protected void addModules() {
        addModule(idEnabledConversionServiceModule);
    }

    @Override
    protected void updateDtoFromEntity(final Player dto, final PlayerEntity entity) {
        dto.setNickname(entity.getNickname());
        dto.setDeletable(true);
    }

    @Override
    public void updateEntityFromDto(final Player dto, final PlayerEntity entity) {
        entity.setNickname(dto.getNickname());
    }

}
