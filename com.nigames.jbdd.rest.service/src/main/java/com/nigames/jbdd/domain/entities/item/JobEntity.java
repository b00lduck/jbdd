package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.subitem.ProductionEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database Entity for Jobs.
 *
 * @author Daniel
 */
@Entity
@Table(name = "ITEM_JOB")
@Inheritance(strategy = InheritanceType.JOINED)
public class JobEntity extends AbstractItemEntity {

	/**
	 * This is a passive backlink. Gets all {@link com.nigames.jbdd.domain.entities.subitem.ProductionEntity} objects who use this Good.
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "job", updatable = false, insertable = false)
	private final List<BuildingJobEntity> referencedBuildingJob = new ArrayList<>();

	/**
	 * The goods produced and consumed by this job as a list of {@link com.nigames.jbdd.domain.entities.subitem.ProductionEntity}.
	 */
	@OneToMany(mappedBy = "id.jobId", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<ProductionEntity> productionList = new ArrayList<>();

	/**
	 * Create instance and setup/link facet instances.
	 */
	public static JobEntity newInstance() {
		final JobEntity entity = new JobEntity();
		initInstance(entity);
		return entity;
	}

	public List<ProductionEntity> getProductionList() {
		return productionList;
	}

	public void setProductionList(final List<ProductionEntity> productionList) {
		this.productionList = productionList;
	}

    // TODO: equals and hashcode

}
