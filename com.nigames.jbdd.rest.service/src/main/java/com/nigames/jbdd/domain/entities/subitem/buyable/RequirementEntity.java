package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Database entity of the {@link AbstractBuyableSubItemEntity} Requirement. The data is kept in the
 * composite primary key {@link RequirementEntityPK}.
 *
 * @author Daniel
 */
@Entity
@Table(name = "requirement")
@NamedQueries(@NamedQuery(name = "findRequirementsByBuyable",
        query = "FROM RequirementEntity WHERE buyable=:buyable"))
public class RequirementEntity extends AbstractBuyableSubItemEntity {

    /**
     * The embedded primary key.
     */
    @EmbeddedId
    private RequirementEntityPK id = new RequirementEntityPK();

    /**
     * The buyable.
     *
    @ManyToOne
    @MapsId("buyableId")
    @JoinColumn(name = "buyable_id", referencedColumnName = "id")
    private BuyableEntityFacetImpl buyable;

    /**
     * The required buyable.
     *
    @ManyToOne
    @MapsId("requiredBuyableId")
    @JoinColumn(name = "required_buyable_id", referencedColumnName = "id")
    private BuyableEntityFacetImpl requiredBuyable;

    /**
     * The JPA version field.
     *
    @Version
    private Integer version;

    /**
     * @return Get {@link RequirementEntity#id}
     */
    public RequirementEntityPK getId() {
        return id;
    }

    /**
     * @param id The {@link RequirementEntity#id} to set
     */
    public void setId(final RequirementEntityPK id) {
        this.id = id;
    }

    /**
     * @return Get {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl}
     *
    public BuyableEntityFacetImpl getBuyable() {
        return buyable;
    }

    /**
     * @param buyable The {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl} to set
     *
    public void setBuyable(final BuyableEntityFacetImpl buyable) {
        this.buyable = buyable;
    }

    /**
     * @return Get the required {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl}
     *
    public BuyableEntityFacetImpl getRequiredBuyable() {
        return requiredBuyable;
    }

    /**
     * @param requiredBuyable The required {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl} to set
     *
    public void setRequiredBuyable(final BuyableEntityFacetImpl requiredBuyable) {
        this.requiredBuyable = requiredBuyable;
    }
                              */

}
