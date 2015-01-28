package com.nigames.jbdd.domain.entities.item;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Database entity for Technologies.
 *
 * @author Daniel
 */
@Entity
@Table(name = "technology")
@NamedQueries(@NamedQuery(name = TechnologyEntity.NQ_FIND_ALL_ENABLED_TECHNOLOGIES,
        query = "SELECT t FROM TechnologyEntity t WHERE t.enabled=1"))
public class TechnologyEntity extends AbstractItemEntity {

        public static final String NQ_FIND_ALL_ENABLED_TECHNOLOGIES = "findAllEnabledTechnologies";
}
