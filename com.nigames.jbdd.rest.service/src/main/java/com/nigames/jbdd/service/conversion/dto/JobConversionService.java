package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.item.JobEntity;
import com.nigames.jbdd.rest.dto.Job;
import com.nigames.jbdd.service.conversion.dto.module.IdEnabledConversionServiceModule;
import com.nigames.jbdd.service.conversion.dto.module.NameDescConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobConversionService extends AbstractConversionService<JobEntity, Job> {

    @Autowired
    private transient NameDescConversionServiceModule nameDescConversionServiceModule;

    @Autowired
    private transient IdEnabledConversionServiceModule idEnabledConversionServiceModule;

    @Override
    public JobEntity getNewEntityInstance() {
        return new JobEntity();
    }

    @Override
    public Job getNewDtoInstance(Class<?> entityClass) {
        return new Job();
    }

    @Override
    protected void addModules() {
        addModule(nameDescConversionServiceModule);
        addModule(idEnabledConversionServiceModule);
    }

    @Override
    protected void updateDtoFromEntity(final Job dto, final JobEntity entity) {
        // Nothing to do here (yet).
    }

    @Override
    public void updateEntityFromDto(final Job dto, final JobEntity entity) {
        // Nothing to do here (yet).
    }

}
