package com.nigames.jbdd.service.service;

import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract service implementation for all services based on Spring Data JPA.
 *
 * @param <EntityType> Type of the entity
 * @param <KeyType>
 * @param <DtoType>
 * @author Daniel
 */
public abstract class AbstractRepositoryBackedService<EntityType, KeyType extends Serializable,
		DtoType extends IsDto> {

	protected static Pageable createPageable(final LimitParams limitParams, final SortParams sortParams) {

		final long page = limitParams.getFirst() / limitParams.getSize();
		final long size = limitParams.getSize();

		if (sortParams != null) {

			final Sort.Direction direction;

			if (sortParams.isDesc()) {
				direction = Sort.Direction.DESC;
			} else {
				direction = Sort.Direction.ASC;
			}

			if (sortParams.getSort() != null) {
				final List<String> sortColumns = Arrays.asList(sortParams.getSort());
				final Sort sort = new Sort(direction, sortColumns);
				return new PageRequest((int) page, (int) size, sort);
			}

		}

		return new PageRequest((int) page, (int) size);

	}

	protected abstract PagingAndSortingRepository<EntityType, KeyType> getRepository();

	protected abstract ConversionServiceInterface<EntityType, DtoType> getConversionService();

	@Transactional
	public DtoType create(final DtoType dto) {
		final EntityType entity = getConversionService().convertToEntity(dto);
		getRepository().save(entity);
		return getConversionService().convertToDto(entity);
	}

	@Transactional
	public DtoType update(final KeyType id, final DtoType dto) {
		final EntityType entity = getRepository().findOne(id);
		getConversionService().updateEntity(dto, entity);
		return getConversionService().convertToDto(entity);
	}

	@Transactional
	public void delete(final KeyType id) {
		getRepository().delete(id);
	}

	@Transactional
	public long getCount() {
		return getRepository().count();
	}

	@Transactional
	public List<DtoType> findAll() {
		return findAll(LimitParams.createDefault(), SortParams.createDefault());
	}

	@Transactional
	public List<DtoType> findAll(final LimitParams limitParams, final SortParams sortParams) {
		final Page p = getPage(limitParams, sortParams);
		return getConversionService().convertToDto(p.getContent());
	}

	@Transactional
	public DtoType findById(final KeyType id) {
		final EntityType entity = getRepository().findOne(id);
		return getConversionService().convertToDto(entity);
	}

	private Page<EntityType> getPage(final LimitParams limitParams, final SortParams sortParams) {
		final Pageable pageable = createPageable(limitParams, sortParams);
		return getRepository().findAll(pageable);
	}

}
