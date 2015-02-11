package com.nigames.jbdd.domain.entities.item;

import javax.persistence.*;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 11.02.2015.
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
@Entity
@Table(name = "ITEM_BUILDING_JOB")
public class BuildingJobEntity {

	/**
	 * The embedded primary key.
	 */
	@EmbeddedId
	private final BuildingJobEntityPK id = new BuildingJobEntityPK();

	private long efficiency;

	/**
	 *
	 */
	@MapsId("buildingId")
	@JoinColumn(name = "building", referencedColumnName = "id", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private BuildingEntity building;

	/**
	 *
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("jobId")
	@JoinColumn(name = "job", referencedColumnName = "id", updatable = false, insertable = false)
	private JobEntity job;

	public BuildingEntity getBuilding() {
		return building;
	}

	public void setBuilding(final BuildingEntity building) {
		this.building = building;
	}

	public JobEntity getJob() {
		return job;
	}

	public void setJob(final JobEntity job) {
		this.job = job;
	}

	public long getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(final long efficiency) {
		this.efficiency = efficiency;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BuildingJobEntity)) {
			return false;
		}

		final BuildingJobEntity that = (BuildingJobEntity) o;

		if (efficiency != that.efficiency) {
			return false;
		}
		if (!building.equals(that.building)) {
			return false;
		}
		return job.equals(that.job);

	}

	@Override
	public int hashCode() {
		int result = (int) (efficiency ^ (efficiency >>> 32));
		result = (31 * result) + building.hashCode();
		result = (31 * result) + job.hashCode();
		return result;
	}

}


