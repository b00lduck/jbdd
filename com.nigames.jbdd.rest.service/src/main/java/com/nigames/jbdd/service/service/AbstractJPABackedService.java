package com.nigames.jbdd.service.service;

import com.nigames.jbdd.rest.dto.facet.IsDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Abstract service implementation for all services.
 *
 * @param <EntityType> Type of the entity
 * @param <DtoType>    Type of the DTO
 * @author Daniel
 */
public abstract class AbstractJPABackedService<EntityType, DtoType extends IsDto> {

    /**
     * The entity manager.
     */
    @PersistenceContext
    private EntityManager entityManager;

    protected abstract Class<EntityType> getEntityClass();

    /**
     * Get the entity manager.
     *
     * @return EntityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}
