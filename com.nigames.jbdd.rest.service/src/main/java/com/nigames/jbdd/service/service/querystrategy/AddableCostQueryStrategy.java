package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.types.SortParams;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import java.util.Arrays;

@Component
public class AddableCostQueryStrategy extends AbstractQueryStrategy<GoodEntity> {

	@Override
	protected Class<GoodEntity> getEntityClass() {
		return GoodEntity.class;
	}

	@SuppressWarnings({"RefusedBequest", "StringConcatenation"})
	@Override
	public TypedQuery<GoodEntity> constructSortedQuery(final SortParams sortParams, final Object... queryParams) {
		final SortParams fixedSortParams = SortParams.createFixed(sortParams, Arrays.asList("id"));
		final String queryName = getSortedQueryName(fixedSortParams);
		final TypedQuery<GoodEntity> ret = createNamedQuery(queryName);
		return ret;
	}

	@Override
	public TypedQuery<Long> constructCountQuery(final Object... queryParams) {
		return entityManager.createNamedQuery(GoodEntity.NQ_ADDABLE_COST_COUNT, Long.class);
	}

	private final String getSortedQueryName(final SortParams sortParams) {

		switch (sortParams.getSort()) {

			case "id":
				if (sortParams.isDesc()) {
					return GoodEntity.NQ_ADDABLE_COST_SORTED_BY_ID_DESC;
				} else {
					return GoodEntity.NQ_ADDABLE_COST_SORTED_BY_ID;
				}

		}

		return GoodEntity.NQ_ADDABLE_COST_SORTED_BY_ID;
	}

}
