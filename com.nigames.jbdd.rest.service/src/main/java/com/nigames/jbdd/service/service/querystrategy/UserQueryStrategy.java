package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.types.SortParams;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

@Component
public class UserQueryStrategy extends AbstractQueryStrategy<UserEntity> {

    @Override
    public TypedQuery<UserEntity> constructSortedQuery(final SortParams sortParams, final Object... queryParams) {

        final List<String> constructedFields = Arrays.asList("id", "enabled", "username", "email");
        //final List<String> namedFields = Arrays.asList("numPlayers", "adminUserRole", "playerRole");

        if (constructedFields.contains(sortParams.getSort()) || (null == sortParams.getSort())) {
            return super.constructSortedQuery(sortParams);
        } else {
            return getNamedSortQuery(sortParams);
        }

    }

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }

}
