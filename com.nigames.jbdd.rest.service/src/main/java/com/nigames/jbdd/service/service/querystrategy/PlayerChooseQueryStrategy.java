package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.types.SortParams;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.Arrays;

@Component
public class PlayerChooseQueryStrategy extends AbstractQueryStrategy<PlayerEntity> {

    @Override
    protected Class<PlayerEntity> getEntityClass() {
        return PlayerEntity.class;
    }

    @SuppressWarnings({"RefusedBequest", "StringConcatenation"})
    @Override
    public TypedQuery<PlayerEntity> constructSortedQuery(final SortParams sortParams, final Object... queryParams) {

        final SortParams fixedSortParams = SortParams.createFixed(sortParams, Arrays.asList("id", "enabled", "nickname"));

        final String queryName = getSortedQueryName(fixedSortParams);

	    return createNamedQuery(queryName);

    }

    private final String getSortedQueryName(final SortParams sortParams) {

        switch (sortParams.getSort()) {

            case "id":
                if (sortParams.isDesc()) {
                    return PlayerEntity.NQ_UNUSED_SORTED_BY_ID_DESC;
                } else {
                    return PlayerEntity.NQ_UNUSED_SORTED_BY_ID;
                }

            case "enabled":
                if (sortParams.isDesc()) {
                    return PlayerEntity.NQ_UNUSED_SORTED_BY_ENABLED_DESC;
                } else {
                    return PlayerEntity.NQ_UNUSED_SORTED_BY_ENABLED;
                }


            case "nickname":
                if (sortParams.isDesc()) {
                    return PlayerEntity.NQ_UNUSED_SORTED_BY_NICKNAME_DESC;
                } else {
                    return PlayerEntity.NQ_UNUSED_SORTED_BY_NICKNAME;
                }

	        default:
		        return PlayerEntity.NQ_UNUSED_SORTED_BY_ID;

        }

    }

}
