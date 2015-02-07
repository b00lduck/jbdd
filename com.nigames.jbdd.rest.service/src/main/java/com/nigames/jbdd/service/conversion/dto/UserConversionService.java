package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.service.conversion.dto.module.IdEnabledConversionServiceModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConversionService extends AbstractConversionService<UserEntity, User> {

    @Autowired
    private IdEnabledConversionServiceModule idEnabledConversionServiceModule;

    @Override
    protected void addModules() {
        addModule(idEnabledConversionServiceModule);
    }

    @Override
    public UserEntity getNewEntityInstance() {
        return new UserEntity();
    }

    @Override
    public User getNewDtoInstance(final Class<?> entityClass) {
        return new User();
    }

    public User convertToDtoWithPassword(final UserEntity entity) {
	    final User ret = convertToDto(entity);
	    ret.setPassword(entity.getPassword());
        return ret;
    }

    @Override
    public void updateEntityFromDto(final User dto, final UserEntity entity) {
        entity.setUsername(dto.getUsername());
        entity.setEmail(dto.getEmail());
        entity.setEnabled(dto.isEnabled());

	    // only set the password if it is provided
	    if (null != dto.getPassword()) {
            entity.setPassword(dto.getPassword());
        }

    }

	@Override
	protected void updateDtoFromEntity(final User dto, final UserEntity entity) {
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());

        //dto.setPassword(entity.getPassword());

        dto.setRoles(entity.getUserRoleList());
        dto.setNumPlayers(entity.getPlayerList().size());

		dto.setDeletable(!"admin".equals(dto.getUsername()) && (0 == dto.getNumPlayers()));
	}

}
