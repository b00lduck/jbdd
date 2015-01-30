package com.nigames.jbdd.domain.entities.item;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Database entity for {@link com.nigames.jbdd.domain.entities.item.StoragetypeEntity}.
 *
 * @author Daniel
 */
@SuppressWarnings("EmptyClass")
@Entity
@Table(name = "ITEM_STORAGETYPE")
@Inheritance(strategy = InheritanceType.JOINED)
public class StoragetypeEntity extends AbstractItemEntity {

    //@Override
    //public Long getNumReferences() {
    //	return 0L;
    //}

    // TODO: implements getNumReferences

}
