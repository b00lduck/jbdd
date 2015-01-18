package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.JobEntity;
import org.springframework.stereotype.Component;

@Component
public class JobQueryStrategy extends AbstractQueryStrategy<JobEntity> {

    @Override
    protected Class<JobEntity> getEntityClass() {
        return JobEntity.class;
    }

}
