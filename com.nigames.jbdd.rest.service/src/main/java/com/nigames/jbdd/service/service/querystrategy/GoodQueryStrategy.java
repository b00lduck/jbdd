package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import org.springframework.stereotype.Component;

@Component
public class GoodQueryStrategy extends AbstractQueryStrategy<GoodEntity> {

    @Override
    protected Class<GoodEntity> getEntityClass() {
        return GoodEntity.class;
    }

}
