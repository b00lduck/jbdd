package com.nigames.jbdd.service.service;

import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;

import java.util.List;

/**
 * Abstract service class interface.
 *
 * @param <DtoType>    Type of the DTO, must implement {@link com.nigames.jbdd.rest.dto.facet.IsDto}
 * @author Daniel
 *
 */
public interface AbstractDtoServiceInterface<DtoType extends IsDto> {

    DtoType create(final DtoType dto);

	DtoType update(final Long id, final DtoType dto);

	void delete(final Long id);

	long getCount();

    List<DtoType> findAll(final LimitParams limitParams, final SortParams sortParams);

	DtoType findById(final Long entityId);

}
