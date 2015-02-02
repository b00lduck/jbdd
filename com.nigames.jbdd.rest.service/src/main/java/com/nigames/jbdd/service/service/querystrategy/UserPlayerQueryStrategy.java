package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.types.SortParams;
import org.springframework.stereotype.Component;

import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

@Component
public final class UserPlayerQueryStrategy extends AbstractQueryStrategy<PlayerEntity> {

	@Override
	protected final Class<PlayerEntity> getEntityClass() {
		return PlayerEntity.class;
	}

	@Override
	protected List<String> getSpecialSortColumns() {
		return Arrays.asList("id", "enabled", "nickname");
	}

	@Override
	protected TypedQuery<PlayerEntity> createSpecialSortQuery(@NotNull final SortParams sortParams, Object... queryParams) {
		final SortParams fixedSortParams = SortParams.createFixed(sortParams, Arrays.asList("id", "enabled", "nickname"));
		final String queryName = getSortedQueryName(fixedSortParams);
		final TypedQuery<PlayerEntity> ret = createNamedQuery(queryName);
		ret.setParameter("userid", queryParams[0]);
		return ret;
	}

	@SuppressWarnings("RefusedBequest")
	@Override
	public TypedQuery<Long> constructCountQuery(final Object... queryParams) {
		final TypedQuery<Long> ret = entityManager.createNamedQuery(PlayerEntity.NQ_COUNT_BY_USER_ID, Long.class);
		ret.setParameter("userid", queryParams[0]);
		return ret;
	}

	private final String getSortedQueryName(final SortParams sortParams) {
		switch (sortParams.getSort()) {
			case "id":
				if (sortParams.isDesc()) {
					return PlayerEntity.NQ_BY_USER_SORTED_BY_ID_DESC;
				} else {
					return PlayerEntity.NQ_BY_USER_SORTED_BY_ID;
				}
			case "enabled":
				if (sortParams.isDesc()) {
					return PlayerEntity.NQ_BY_USER_SORTED_BY_ENABLED_DESC;
				} else {
					return PlayerEntity.NQ_BY_USER_SORTED_BY_ENABLED;
				}
			case "nickname":
				if (sortParams.isDesc()) {
					return PlayerEntity.NQ_BY_USER_SORTED_BY_NICKNAME_DESC;
				} else {
					return PlayerEntity.NQ_BY_USER_SORTED_BY_NICKNAME;
				}
			default:
				return PlayerEntity.NQ_BY_USER_SORTED_BY_ID;

		}

	}

}
