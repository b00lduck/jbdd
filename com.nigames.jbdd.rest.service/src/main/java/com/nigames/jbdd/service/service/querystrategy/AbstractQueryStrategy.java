package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.statics.Languages;
import com.nigames.jbdd.types.SortParams;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public abstract class AbstractQueryStrategy<EntityType> implements QueryStrategy<EntityType> {

	@PersistenceContext
	protected EntityManager entityManager;

	protected abstract Class<EntityType> getEntityClass();

	protected TypedQuery<EntityType> createSpecialSortQuery(@NotNull final SortParams sortParams,
	                                                        final Object... queryParams) {
		throw new IllegalArgumentException("A special sort column were defined," +
				" but createSpecialSortQuery not overloaded.");
	}

	protected List<String> getCriteriaSortColumns() {
		return new ArrayList<>();
	}

	protected List<String> getSpecialSortColumns() {
		return new ArrayList<>();
	}

	@Override
	public TypedQuery<Long> constructCountQuery(final Object... queryParams) {
		final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
		criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(getEntityClass())));
		return entityManager.createQuery(criteriaQuery);
	}


	@Override
	public final TypedQuery<EntityType> constructSortedQuery(@NotNull final SortParams sortParams, final Object... queryParams) {

		if (null == sortParams) {
			throw new NullPointerException("sortParams parameter must not be null");
		}

		if (null == sortParams.getSort()) {
			throw new NullPointerException("sort column must not be null");
		}

		if (getSpecialSortColumns().contains(sortParams.getSort())) {
			return createSpecialSortQuery(sortParams, queryParams);
		}

		if (getCriteriaSortColumns().contains(sortParams.getSort())) {

			final Boolean desc = sortParams.isDesc();
			final String sort = sortParams.getSort();

			final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
			final CriteriaQuery<EntityType> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
			final Root<EntityType> root = criteriaQuery.from(getEntityClass());

			if (null != sort) {

				if ((null != desc) && desc) {
					criteriaQuery.orderBy(criteriaBuilder.desc(getRoot(sort, root)));
				} else {
					criteriaQuery.orderBy(criteriaBuilder.asc(getRoot(sort, root)));
				}
			}

			return entityManager.createQuery(criteriaQuery);

		}

		throw new IllegalArgumentException("Unknown sort column: " + sortParams.getSort());

	}

	private final Path<EntityType> getRoot(final String sort, final Root<EntityType> root) {
		if (sort.contains(".")) {
			final String sortParts[] = sort.split("\\.");
			if (sortParts[0].equals("name")) {
				if (isValidLangCode(sortParts[1])) {
					final String localeParts[] = sortParts[1].split("-");
					final Path<EntityType> r1 = root.get("nameAndDescFacet");
					final Path<EntityType> r2 = r1.get("name");
					final Path<EntityType> r3 = r2.get(localeParts[0]);
					return r3;
				} else {
					throw new IllegalArgumentException("Illegal language code string " + sortParts[1]);
				}
			} else {
				throw new IllegalArgumentException("Illegal sort string " + sort);
			}

		} else {
			return root.get(sort);
		}
	}

	private final boolean isValidLangCode(final String langCode) {
		for (final Locale l : Languages.getLocaleList()) {
			if (l.toLanguageTag().equals(langCode)) {
				return true;
			}
		}
		return false;
	}

	TypedQuery<EntityType> createNamedQuery(final String queryName) {
		return entityManager.createNamedQuery(queryName, getEntityClass());
	}

}
