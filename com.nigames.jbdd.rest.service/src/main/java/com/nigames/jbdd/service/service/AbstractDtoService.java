package com.nigames.jbdd.service.service;

import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.rest.exceptionprovider.ContentNotFoundException;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract service class implementation for all entities.
 *
 * @param <DtoType>    Type of the Entity, must implement {@link IsDto}
 * @param <EntityType>
 * @author Daniel
 */
@Service
public abstract class AbstractDtoService<DtoType extends IsDto, EntityType>
        extends AbstractJPABackedService<EntityType, DtoType> {

    @Transactional
    protected DtoType create(final DtoType dto) {
        final EntityType entity = getConversionService().convertToEntity(dto);
        getEntityManager().persist(entity);
        return getConversionService().convertToDto(entity);
    }

    @Transactional
    protected DtoType update(final long id, final DtoType dto) {
        final EntityType entity = getEntityManager().find(getEntityClass(), id);
        getConversionService().updateEntity(dto, entity);
        return getConversionService().convertToDto(entity);
    }

    @Transactional
    protected void delete(final long id) {
        final EntityType entity = getEntityManager().find(getEntityClass(), id);
        getEntityManager().remove(entity);
    }

    @Transactional
    public long getCount() {
        final CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        criteriaQuery.select(criteriaBuilder.count(criteriaQuery.from(getEntityClass())));
        final TypedQuery<Long> query = getEntityManager().createQuery(criteriaQuery);
        return query.getSingleResult();
    }

	@Transactional
	public long getCount(final QueryStrategy<EntityType> queryStrategy, final Object... queryParams) {
		final TypedQuery<Long> query = queryStrategy.constructCountQuery(queryParams);
		return query.getSingleResult();
	}

    @Transactional
    protected List<DtoType> findAll(final LimitParams limitParams, final SortParams sortParams) {
        return findAll(limitParams, sortParams, getDefaultQueryStrategy());
    }

    @Transactional
    protected List<DtoType> findAll(final LimitParams limitParams, final SortParams sortParams,
                                    final QueryStrategy<EntityType> queryStrategy, final Object... queryParams) {

        final Long size = limitParams.getSize();
        final Long first = limitParams.getFirst();

        // create the query
        final TypedQuery<EntityType> query =
                queryStrategy.constructSortedQuery(sortParams, queryParams);

        // apply size limiting if applicable
        if (null != size) {
            if (0L > size) {
                throw new IllegalArgumentException("Parameter \"size\" must be positive.");
            }
            if (size.equals(0L)) {
                return new ArrayList<>();
            }
            query.setMaxResults(size.intValue());
        }

        // apply first result offset
        if (null != first) {
            if (0L > first) {
                throw new IllegalArgumentException("Parameter \"first\" must be positive.");
            }
            query.setFirstResult(first.intValue());
        }

        return getConversionService().convertToDto(query.getResultList());
    }

    @Transactional
    protected DtoType findById(final long entityId) {
        final EntityType entity = getEntityManager().find(getEntityClass(), entityId);
        if (null == entity) {
            throw new ContentNotFoundException();
        }
        return getConversionService().convertToDto(entity);
    }

    protected abstract ConversionServiceInterface<EntityType, DtoType> getConversionService();

    protected abstract QueryStrategy<EntityType> getDefaultQueryStrategy();
}
