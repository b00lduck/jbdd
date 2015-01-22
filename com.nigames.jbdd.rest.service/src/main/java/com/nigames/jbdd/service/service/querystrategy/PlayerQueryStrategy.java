package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import org.springframework.stereotype.Component;

@Component
public class PlayerQueryStrategy extends AbstractQueryStrategy<PlayerEntity> {

    @Override
    protected Class<PlayerEntity> getEntityClass() {
        return PlayerEntity.class;
    }

}
