/**
 *
 */
package com.nigames.jbdd.service.service.subitem.player;

import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.rest.dto.aspects.IsDto;
import com.nigames.jbdd.service.service.subitem.AbstractSubService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

/**
 * Abstract service implementation for playerSubItem item.
 *
 * @param <DtoType> the class parameter, type of the playerSubItem item
 */
@SuppressWarnings("AbstractClassNeverImplemented")
public abstract class AbstractPlayerSubService<DtoType extends IsDto, EntityType>
        extends AbstractSubService<DtoType, EntityType, Player> {

    private static final Logger LOG = LoggerFactory
            .getLogger(AbstractPlayerSubService.class);

    @Override
    @Transactional
    public final void update(final long id, final DtoType dto) {

        // if (getEntityManager().contains(entity)) {
        // getEntityManager().merge(entity);
        // } else {
        // DtoType attachedEntity = getAttachedItem(entity);
        // copyHook(entity, attachedEntity);
        // }
        LOG.error("NOT IMPLEMENTED"); // NON-NLS
        // TODO: implement

    }

}
