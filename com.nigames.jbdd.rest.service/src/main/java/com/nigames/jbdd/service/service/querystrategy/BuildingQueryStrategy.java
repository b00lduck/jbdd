package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import org.springframework.stereotype.Component;

@Component
public class BuildingQueryStrategy extends AbstractQueryStrategy<BuildingEntity> {

    @Override
    protected Class<BuildingEntity> getEntityClass() {
        return BuildingEntity.class;
    }

}
