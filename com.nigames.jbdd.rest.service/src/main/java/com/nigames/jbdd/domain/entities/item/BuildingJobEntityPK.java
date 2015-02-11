package com.nigames.jbdd.domain.entities.item;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 11.02.2015.
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
@Embeddable
public class BuildingJobEntityPK implements Serializable {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1;

	/**
	 * The id of the requiring {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl}.
	 */
	@Column(name = "building")
	private long buildingId;

	/**
	 * The id of the required {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl}.
	 */
	@Column(name = "job")
	private long jobId;

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BuildingJobEntityPK)) {
			return false;
		}

		final BuildingJobEntityPK that = (BuildingJobEntityPK) o;

		if (buildingId != that.buildingId) {
			return false;
		}
		return jobId == that.jobId;

	}

	@Override
	public int hashCode() {
		int result = (int) (buildingId ^ (buildingId >>> 32));
		result = (31 * result) + (int) (jobId ^ (jobId >>> 32));
		return result;
	}

}
