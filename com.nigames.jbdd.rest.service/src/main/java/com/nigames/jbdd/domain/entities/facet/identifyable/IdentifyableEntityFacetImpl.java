package com.nigames.jbdd.domain.entities.facet.identifyable;

import javax.persistence.*;

@MappedSuperclass
public abstract class IdentifyableEntityFacetImpl implements IdentifyableEntityFacet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version;

    @Override
    public long getId() {
        return id;
    }

	@Override
	public final boolean equals(final Object o) {

		if (this == o) {
			return true;
		}

		if (!(o instanceof IdentifyableEntityFacetImpl)) {
			return false;
		}

		final IdentifyableEntityFacetImpl that = (IdentifyableEntityFacetImpl) o;

		return id == that.id;

	}

	@Override
	public final int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

}
