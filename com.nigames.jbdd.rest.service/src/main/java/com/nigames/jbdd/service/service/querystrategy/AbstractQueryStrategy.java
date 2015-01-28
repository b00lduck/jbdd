package com.nigames.jbdd.service.service.querystrategy;

import com.nigames.jbdd.types.SortParams;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.validation.constraints.NotNull;
import java.util.Locale;

public abstract class AbstractQueryStrategy<EntityType> implements QueryStrategy<EntityType> {

    @PersistenceContext
    private transient EntityManager entityManager;

    protected abstract Class<EntityType> getEntityClass();

    @SuppressWarnings({"StringConcatenationMissingWhitespace", "StringConcatenation"})
    private String getNamedSortQueryName(final SortParams sortParams) {

        final String entityName = getEntityClass().getSimpleName();

        String ret = sortParams.getSort().substring(1);
        ret = entityName + ".findAllSortedBy" + sortParams.getSort().substring(0, 1).toUpperCase(Locale.ENGLISH) + ret;

        ret = appendDesc(ret, sortParams.isDesc());
        return ret;
    }

    @SuppressWarnings({"StringConcatenation", "StringConcatenationMissingWhitespace"})
    String appendDesc(final String str, final Boolean desc) {
        return desc.equals(Boolean.TRUE) ? (str + "Desc") : str;
    }

    TypedQuery<EntityType> getNamedSortQuery(final SortParams sortParams) {
        return entityManager.createNamedQuery(getNamedSortQueryName(sortParams), getEntityClass());
    }

    @Override
    public TypedQuery<EntityType> constructSortedQuery(@NotNull final SortParams sortParams, final Object... queryParams) {

        if (null == sortParams) {
            throw new NullPointerException("sortParams parameter must not be null");
        }

        final Boolean desc = sortParams.isDesc();
        final String sort = sortParams.getSort();

        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<EntityType> criteriaQuery = criteriaBuilder.createQuery(getEntityClass());
        final Root<EntityType> root = criteriaQuery.from(getEntityClass());

        if (null != sort) {
            if ((null != desc) && desc) {
                criteriaQuery.orderBy(criteriaBuilder.desc(root.get(sort)));
            } else {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get(sort)));
            }
        }

        return entityManager.createQuery(criteriaQuery);
    }

    TypedQuery<EntityType> createNamedQuery(final String queryName) {
        return entityManager.createNamedQuery(queryName, getEntityClass());
    }

}
