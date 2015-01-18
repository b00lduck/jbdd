package com.nigames.jbdd.domain.entities.subitem.buyable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for Production.
 *
 * @author Daniel
 */
@Embeddable
public class ProductionEntityPK implements Serializable {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 726773201509770282L;

    /**
     * The id of the producing/consuming {@link com.nigames.jbdd.domain.entities.item.BuildingEntity}.
     */
    @Column(name = "building_id")
    private Long buildingId;

    /**
     * The id of the produced/consumed {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @Column(name = "good_id")
    private Long goodId;

    /**
     * @return Get {@link ProductionEntityPK#buildingId}
     */
    public Long getBuildingId() {
        return buildingId;
    }

    /**
     * @return Get {@link ProductionEntityPK#goodId}
     */
    public Long getGoodId() {
        return goodId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (null == buildingId ? 0 : buildingId.hashCode());
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
        if (!(obj instanceof ProductionEntityPK)) {
            return false;
        }
        final ProductionEntityPK other = (ProductionEntityPK) obj;
        if (null == buildingId) {
            if (null != other.buildingId) {
                return false;
            }
        } else if (!buildingId.equals(other.buildingId)) {
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
