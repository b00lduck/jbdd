package com.nigames.jbdd.domain.entities.subitem.buyable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for {@link CostEntity}.
 *
 * @author Daniel
 */
@Embeddable
public final class CostEntityPK implements Serializable {

	/**
	 * The serialVersionUID.
	 */
	private static final long serialVersionUID = 1;

	/**
	 * The id of the {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl}.
	 */
	@Column(name = "buyable")
	private long buyableId;

	/**
	 * The id of the {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
	 */
	@Column(name = "good")
	private long goodId;

	public CostEntityPK() {
	}

	public CostEntityPK(final long buyableId, final long goodId) {
		this.buyableId = buyableId;
		this.goodId = goodId;
	}

	/**
	 * @return Get {@link CostEntityPK#buyableId}
	 */
	public long getBuyableId() {
		return buyableId;
	}

	/**
	 * @return Get {@link CostEntityPK#goodId}
	 */
	public long getGoodId() {
		return goodId;
	}

	@Override
	public int hashCode() {
		int result = (int) (buyableId ^ (buyableId >>> 32));
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
		if (!(obj instanceof CostEntityPK)) {
			return false;
		}
		final CostEntityPK other = (CostEntityPK) obj;
		if (buyableId != other.buyableId) {
			return false;
		}
		return goodId == other.goodId;
	}

}
