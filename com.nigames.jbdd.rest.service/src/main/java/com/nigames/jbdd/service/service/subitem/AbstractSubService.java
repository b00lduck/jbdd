package com.nigames.jbdd.service.service.subitem;

import com.nigames.jbdd.rest.dto.aspects.IsDto;
import com.nigames.jbdd.service.service.AbstractJPABackedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Abstract service implementation for all services.
 *
 * @param <DtoType>
 * @author Daniel
 */
@SuppressWarnings("AbstractClassNeverImplemented")
public abstract class AbstractSubService<DtoType extends IsDto, EntityType, ParentType>
        extends AbstractJPABackedService<EntityType, DtoType> {

    private static final Logger LOG = LoggerFactory
            .getLogger(AbstractSubService.class);

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Transactional
    public final void create(final long parentId, final DtoType entity) {
        // TODO: implement
        // getEntityManager().persist(entity);
        LOG.error("NOT IMPLEMENTED"); // NON-NLS
    }

    @SuppressWarnings("DuplicateStringLiteralInspection")
    @Transactional
    public final void remove(final long id) {
        // TODO: implement
        // if (getEntityManager().contains(entity)) {
        // getEntityManager().remove(entity);
        // } else {
        // DtoType attached = getAttachedItem(entity);
        // getEntityManager().remove(attached);
        // }
        LOG.error("NOT IMPLEMENTED"); // NON-NLS
    }

    public abstract List<DtoType> getAll(final long id);

    public abstract void update(final long id, final DtoType dto);

}
