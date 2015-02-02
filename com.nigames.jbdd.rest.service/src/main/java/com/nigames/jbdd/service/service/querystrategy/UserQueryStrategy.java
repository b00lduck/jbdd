package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.auth.UserEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class UserQueryStrategy extends AbstractQueryStrategy<UserEntity> {

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

	protected List<String> getCriteriaSortColumns() {
		return Arrays.asList("id", "username", "email", "enabled");
	}

	protected List<String> getSpecialSortColumns() {
		return Arrays.asList("numPlayers", "deletable");
	}

}
