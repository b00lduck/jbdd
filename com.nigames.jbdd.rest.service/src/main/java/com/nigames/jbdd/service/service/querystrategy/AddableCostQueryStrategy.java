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

	@SuppressWarnings("RefusedBequest")
	@Override
	public TypedQuery<GoodEntity> constructSortedQuery(final SortParams sortParams, final Object... queryParams) {
		final SortParams fixedSortParams = SortParams.createFixed(sortParams, Arrays.asList("id"));
		final String queryName = getSortedQueryName(fixedSortParams);
		final TypedQuery<GoodEntity> ret = createNamedQuery(queryName);
		ret.setParameter("buyableId", queryParams[0]);
		return ret;
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	public TypedQuery<Long> constructCountQuery(final Object... queryParams) {
		final TypedQuery<Long> ret = entityManager.createNamedQuery(GoodEntity.NQ_ADDABLE_COST_COUNT, Long.class);
		ret.setParameter("buyableId", queryParams[0]);
		return ret;
	}

	private String getSortedQueryName(final SortParams sortParams) {

		switch (sortParams.getSort()) {

			case "id":
				if (sortParams.isDesc()) {
					return GoodEntity.NQ_ADDABLE_COST_SORTED_BY_ID_DESC;
				} else {
					return GoodEntity.NQ_ADDABLE_COST_SORTED_BY_ID;
				}

			default:

				return GoodEntity.NQ_ADDABLE_COST_SORTED_BY_ID;
		}

	}

}