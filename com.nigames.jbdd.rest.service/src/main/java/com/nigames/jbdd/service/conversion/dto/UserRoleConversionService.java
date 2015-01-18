package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.auth.UserRoleEntity;
import com.nigames.jbdd.rest.dto.UserRole;
import com.nigames.jbdd.service.conversion.dto.module.IdConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleConversionService extends AbstractConversionService<UserRoleEntity, UserRole> {

    @Autowired
    private transient IdConversionServiceModule idConversionServiceModule;

    @Override
    protected void addModules() {
        addModule(idConversionServiceModule);
    }

    @Override
    public UserRoleEntity getNewEntityInstance() {
        return new UserRoleEntity();
    }

    @Override
    public UserRole getNewDtoInstance() {
        return new UserRole();
    }

    @Override
    protected void updateDtoFromEntity(final UserRole dto, final UserRoleEntity entity) {
        dto.setRoleName(entity.getRole());
    }

    @Override
    public void updateEntityFromDto(final UserRole dto, final UserRoleEntity entity) {
        // nothing to do here.
    }

}
