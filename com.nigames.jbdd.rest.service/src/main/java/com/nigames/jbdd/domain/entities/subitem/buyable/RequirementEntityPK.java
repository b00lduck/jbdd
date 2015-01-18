package com.nigames.jbdd.domain.entities.subitem.buyable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Composite primary key for Requirement.
 *
 * @author Daniel
 */
@Embeddable
public class RequirementEntityPK implements Serializable {

    /**
     * The serialVersionUID.
     */
    private static final long serialVersionUID = 6543507212031496817L;

    /**
     * The id of the requiring {@link com.nigames.jbdd.domain.entities.aspect.BuyableEntityAspectImpl}.
     */
    @Column(name = "buyable_id")
    private Long buyableId;

    /**
     * The id of the required {@link com.nigames.jbdd.domain.entities.aspect.BuyableEntityAspectImpl}.
     */
    @Column(name = "required_buyable_id")
    private Long requiredBuyableId;

    /**
     * @return Get {@link RequirementEntityPK#buyableId}
     */
    public Long getBuyableId() {
        return buyableId;
    }

    /**
     * @return Get {@link RequirementEntityPK#requiredBuyableId}
     */
    public Long getRequiredBuyableId() {
        return requiredBuyableId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (null == buyableId ? 0 : buyableId.hashCode());
        result =
                prime * result + (null == requiredBuyableId ? 0 : requiredBuyableId.hashCode());
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
        if (!(obj instanceof RequirementEntityPK)) {
            return false;
        }
        final RequirementEntityPK other = (RequirementEntityPK) obj;
        if (null == buyableId) {
            if (null != other.buyableId) {
                return false;
            }
        } else if (!buyableId.equals(other.buyableId)) {
            return false;
        }
        if (null == requiredBuyableId) {
            if (null != other.requiredBuyableId) {
                return false;
            }
        } else if (!requiredBuyableId.equals(other.requiredBuyableId)) {
            return false;
        }
        return true;
    }

}
