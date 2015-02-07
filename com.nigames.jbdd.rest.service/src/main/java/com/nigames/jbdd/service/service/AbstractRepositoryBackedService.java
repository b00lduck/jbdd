package com.nigames.jbdd.service.service;

import com.google.common.collect.Lists;
import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.statics.Languages;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
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


	@Nonnull
	protected static Pageable createPageable(@Nonnull final LimitParams limitParams, @Nullable final Sort sort) {

		final long page = limitParams.getFirst() / limitParams.getSize();
		final long size = limitParams.getSize();

		if (null != sort) {
			return new PageRequest((int) page, (int) size, sort);
		} else {
			return new PageRequest((int) page, (int) size);
		}

	}

	@Nonnull
	protected static Pageable createPageable(@Nonnull final LimitParams limitParams,
	                                         @Nonnull final SortParams sortParams) {

		return createPageable(limitParams, createSort(sortParams));
	}

	@Nullable
	private static Sort createSort(final SortParams sortParams) {

		if (null != sortParams) {

			final Sort.Direction direction;

			if (sortParams.isDesc()) {
				direction = Sort.Direction.DESC;
			} else {
				direction = Sort.Direction.ASC;
			}

			if (null != sortParams.getSort()) {
				String sortParam = sortParams.getSort();

				if (sortParam.startsWith("name.")) {
					sortParam = "nameAndDescFacet.name." + Languages.tagToDbTag(sortParam.substring(5));
				}

				if (null != sortParam) {
					final List<String> sortColumns = Arrays.asList(sortParam);
					return new Sort(direction, sortColumns);
				}
			}
		}
		return null;
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

		final List<EntityType> list = getList(limitParams, sortParams);
		return getConversionService().convertToDto(list);

	}

	@Transactional
	public DtoType findById(final KeyType id) {
		final EntityType entity = getRepository().findOne(id);
		return getConversionService().convertToDto(entity);
	}

	private List<EntityType> getList(final LimitParams limitParams, final SortParams sortParams) {

		final Sort sort = createSort(sortParams);

		if (0 == limitParams.getSize()) {
			if (null == sort) {
				return Lists.newArrayList(getRepository().findAll());
			}
			return Lists.newArrayList(getRepository().findAll(sort));
		} else {
			final Pageable pageable = createPageable(limitParams, sort);
			return getRepository().findAll(pageable).getContent();
		}

	}


}
