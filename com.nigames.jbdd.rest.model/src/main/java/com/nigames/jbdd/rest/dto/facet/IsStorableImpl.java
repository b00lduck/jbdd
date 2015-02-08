package com.nigames.jbdd.rest.dto.facet;

public class IsStorableImpl implements IsStorable {

    private int weight;

	private int density;

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

	@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}

		if ((null == o) || (getClass() != o.getClass())) {
			return false;
		}

		final IsStorableImpl that = (IsStorableImpl) o;

		if ((density != that.density) || (weight != that.weight)) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = weight;
		result = (31 * result) + density;
		return result;
	}
}
