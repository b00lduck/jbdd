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
public class CostEntityPK implements Serializable {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 3074751713874794424L;

    /**
     * The id of the {@link com.nigames.jbdd.domain.entities.aspect.BuyableEntityAspectImpl}.
     */
    @Column(name = "buyable_id")
    private Long buyableId;

    /**
     * The id of the {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @Column(name = "good_id")
    private Long goodId;

    /**
     * @return Get {@link CostEntityPK#buyableId}
     */
    public Long getBuyableId() {
        return buyableId;
    }

    /**
     * @return Get {@link CostEntityPK#goodId}
     */
    public Long getGoodId() {
        return goodId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (null == buyableId ? 0 : buyableId.hashCode());
        result = prime * result + (null == goodId ? 0 : goodId.hashCode());
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
        if (null == buyableId) {
            if (null != other.buyableId) {
                return false;
            }
        } else if (!buyableId.equals(other.buyableId)) {
            return false;
        }
        if (null == goodId) {
            if (null != other.goodId) {
                return false;
            }
        } else if (!goodId.equals(other.goodId)) {
            return false;
        }
        return true;
    }

}
