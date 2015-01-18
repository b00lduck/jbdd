package com.nigames.jbdd.service.service.querystrategy;

import javax.persistence.TypedQuery;

public interface QueryStrategy<EntityType> {

    // TODO: refactor queryParams mechanism #51
    TypedQuery<EntityType> constructSortedQuery(SortParams sortParams, Object... queryParams);

}
