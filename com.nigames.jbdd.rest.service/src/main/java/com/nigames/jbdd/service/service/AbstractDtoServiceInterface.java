package com.nigames.jbdd.service.service;

import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;

import java.util.List;

/**
 * Abstract service class interface.
 *
 * @param <DtoType>    Type of the DTO, must implement {@link com.nigames.jbdd.rest.dto.facet.IsDto}
 * @param <EntityType> Type of the Entity
 * @author Daniel
 *         <p>
 *         TODO: narrow the type of EntityType
 */
public interface AbstractDtoServiceInterface<DtoType extends IsDto, EntityType> {

    DtoType create(final DtoType dto);

    DtoType update(final long id, final DtoType dto);

    void delete(final long id);

    long getCount();

	long getCount(final QueryStrategy<EntityType> queryStrategy, final Object... queryParams);

    List<DtoType> findAll(final LimitParams limitParams, final SortParams sortParams);

	List<DtoType> findAll(final LimitParams limitParams, final SortParams sortParams,
	                      final QueryStrategy<EntityType> queryStrategy, final Object... queryParams);

    DtoType findById(final long entityId);

}
