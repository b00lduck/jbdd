package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;

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

	/**
	 * Create instance and setup/link facet instances.
	 */
	public static StoragetypeEntity newInstance() {
		final StoragetypeEntity entity = new StoragetypeEntity();
		initInstance(entity);
		return entity;
	}

    //@Override
    //public Long getNumReferences() {
    //	return 0L;
    //}

    // TODO: implement getNumReferences

}
