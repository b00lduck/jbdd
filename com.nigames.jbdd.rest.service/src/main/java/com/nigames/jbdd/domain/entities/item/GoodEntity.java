package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.facet.HasWeightEntityFacet;
import com.nigames.jbdd.domain.entities.facet.HasWeightEntityFacetImpl;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.ProductionEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database entity for Good.
 *
 * @author Daniel
 */
@Entity
@Table(name = "good")
@NamedQueries(@NamedQuery(name = "findAllEnabledResources", query = "SELECT g FROM GoodEntity g WHERE g.enabled=1"))
public class GoodEntity extends AbstractItemEntity implements HasWeightEntityFacet {

    /**
     * This is a passive backlink. Gets all {@link CostEntity} objects who use this Good.
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "good_id", updatable = false, insertable = false)
    private final List<CostEntity> referencedCosts = new ArrayList<>();
    /**
     * This is a passive backlink. Gets all {@link ProductionEntity} objects who use this Good.
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "good_id", updatable = false, insertable = false)
    private final List<ProductionEntity> referencedProductions = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private final HasWeightEntityFacetImpl hasWeight = new HasWeightEntityFacetImpl();

    @Override
    public int getWeight() {
        return hasWeight.getWeight();
    }

    @Override
    public void setWeight(final int weight) {
        hasWeight.setWeight(weight);
    }

    public List<CostEntity> getReferencedCosts() {
        return referencedCosts;
    }

    public List<ProductionEntity> getReferencedProductions() {
        return referencedProductions;
    }

    // TODO: hashCode, equals and toString

}
