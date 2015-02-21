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

		if (!isEqual(o)) {
			return false;
		}

		final IdentifyableEntityFacetImpl that = (IdentifyableEntityFacetImpl) o;

		return id == that.id;

	}

	@Override
	public final int hashCode() {
		return (int) (id ^ (id >>> 32));
	}

    /**
     * Helpter method used to solve issues with hibernate and euql/hashCode contracts.
     *
     * @see http://docs.jboss.org/hibernate/core/4.0/manual/en-US/html/persistent-classes.html#persistent-classes-equalshashcode
     *
     * @param object
     *
     * @return <code>true</code> when object os of same instance, otherwise <code>false</code>
     */
    protected abstract boolean isEqual(final Object object);
}
