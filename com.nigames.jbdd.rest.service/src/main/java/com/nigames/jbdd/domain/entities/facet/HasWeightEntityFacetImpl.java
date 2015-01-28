package com.nigames.jbdd.domain.entities.facet;

import javax.persistence.*;

/**
 * Abstract Database Entity for all Items.
 *
 * @author Daniel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "weightFacet")
public final class HasWeightEntityFacetImpl implements HasWeightEntityFacet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version;

    private int weight;

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(final int weight) {
        this.weight = weight;
    }
}
