package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.StoragetypeEntity;
import org.springframework.stereotype.Component;

@Component
public class StoragetypeQueryStrategy extends AbstractQueryStrategy<StoragetypeEntity> {

    @Override
    protected Class<StoragetypeEntity> getEntityClass() {
        return StoragetypeEntity.class;
    }

}
