package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.rest.dto.facet.IsDto;

import java.util.List;

/**
 * Public interface for the conversion services from DTO to Entity and vice versa.
 *
 * @param <EntityType> Type of the entity
 * @param <DtoType>    Type of the DTO
 * @author Daniel
 */
public interface ConversionServiceInterface<EntityType, DtoType extends IsDto> {

    /**
     * Converts the given DTO into an entity.
     *
     * @param dto Source DTO
     * @return new entity
     */
    EntityType convertToEntity(final DtoType dto);

    /**
     * Convert the given entity into an DTO.
     *
     * @param entity Source entity
     * @return new DTO
     * @see AbstractConversionService#updateDtoFromEntity
     */
    DtoType convertToDto(final EntityType entity);

    /**
     * Updates the given entity with data from the given DTO.
     *
     * @param dto    Source dto
     * @param entity Target entity
     */
    void updateEntity(final DtoType dto, final EntityType entity);

	/**
	 * Updates the given DTO with data from the given entity.
	 *
	 * @param entity Source entity
	 * @param dto    Target dto
	 */
	void updateDto(final EntityType entity, final DtoType dto);

    /**
     * Converts the given list of entities into an list of DTOs.
     *
     * @param entity List of source entities
     * @return new list of DTOs
     */
    List<DtoType> convertToDto(final List<EntityType> entity);

    /**
     * Converts the given list of DTOs into an list of entities.
     *
     * @param dto List of source DTOs
     * @return new list of entities
     */
    List<EntityType> convertToEntity(final List<DtoType> dto);
}
