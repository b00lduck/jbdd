package com.nigames.jbdd.domain.entities.subitem;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for {@link com.nigames.jbdd.domain.entities.subitem.ProductionEntity}.
 *
 * @author Daniel
 */
@Embeddable
public class ProductionEntityPK implements Serializable {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1;

	/**
	 * The id of the {@link com.nigames.jbdd.domain.entities.item.BuildingEntity}.
	 */
	@Column(name = "job")
	private long jobId;

	/**
	 * The id of the {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
	 */
	@Column(name = "good")
	private long goodId;

	public ProductionEntityPK() {
	}

	public ProductionEntityPK(final long jobId, final long goodId) {
		this.jobId = jobId;
		this.goodId = goodId;
	}

	/**
	 * @return Get {@link com.nigames.jbdd.domain.entities.subitem.ProductionEntityPK#jobId}
	 */
	public long getJobId() {
		return jobId;
	}

	/**
	 * @return Get {@link com.nigames.jbdd.domain.entities.subitem.ProductionEntityPK#goodId}
	 */
	public long getGoodId() {
		return goodId;
	}

	@Override
	public int hashCode() {
		int result = (int) (jobId ^ (jobId >>> 32));
		result = (31 * result) + (int) (goodId ^ (goodId >>> 32));
		return result;
	}

	@SuppressWarnings("VariableNotUsedInsideIf")
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (null == obj) {
			return false;
		}
		if (!(obj instanceof ProductionEntityPK)) {
			return false;
		}
		final ProductionEntityPK other = (ProductionEntityPK) obj;
		if (jobId != other.jobId) {
			return false;
		}
		return goodId == other.goodId;
	}

}
