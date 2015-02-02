package com.nigames.jbdd.domain.entities.item;

import javax.persistence.*;

/**
 * Database Entity for Jobs.
 *
 * @author Daniel
 */
@Entity
@Table(name = "ITEM_JOB")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(@NamedQuery(name = "findAllEnabledJobs", query = "SELECT j FROM JobEntity j WHERE j.enabled=1"))
public class JobEntity extends AbstractItemEntity {

    /**
     * This is a passive backlink. Gets the {@link BuildingEntity} who offers this Job.
     */
    @OneToOne(mappedBy = "job")
    @JoinColumn(updatable = false, insertable = false)
    private BuildingEntity referencedBuilding;

	/**
	 * Create instance and setup/link facet instances.
	 */
	public static JobEntity newInstance() {
		final JobEntity entity = new JobEntity();
		initInstance(entity);
		return entity;
	}

    BuildingEntity getReferencedBuilding() {
        return referencedBuilding;
    }

    public void setReferencedBuilding(final BuildingEntity referencedBuilding) {
        this.referencedBuilding = referencedBuilding;
    }

    // TODO: equals and hashcode

}
