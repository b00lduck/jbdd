package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;

import javax.persistence.*;

/**
 * Abstract Database Entity for all Items.
 *
 * @author Daniel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "FACET_STORABLE")
public final class IsStorableEntityFacetImpl implements IsStorableEntityFacet {

    @Id
    private long id;

    @Version
    private int version;

	@JoinColumn(name = "id")
	@MapsId
	@OneToOne
	private AbstractItemEntity item;

    private int weight;

	private int density;

	private IsStorableEntityFacetImpl() {}

	public IsStorableEntityFacetImpl(final AbstractItemEntity item) {
		this();
		this.item = item;
	}

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(final int weight) {
        this.weight = weight;
    }

	@Override
	public int getDensity() {
		return density;
	}

	@Override
	public void setDensity(final int density) {
		this.density = density;
	}

}
