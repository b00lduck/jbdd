package com.nigames.jbdd.domain.entities.aspect;

import javax.persistence.*;

/**
 * Abstract Database Entity for all Items.
 *
 * @author Daniel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "weightAspect")
public final class HasWeightEntityAspectImpl implements HasWeightEntityAspect {

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
