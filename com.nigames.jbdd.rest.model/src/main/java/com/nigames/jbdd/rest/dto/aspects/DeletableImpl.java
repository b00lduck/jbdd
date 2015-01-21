package com.nigames.jbdd.rest.dto.aspects;

public class DeletableImpl implements Deletable {

	private boolean deletable;

	@Override
	public boolean isDeletable() {
		return deletable;
	}

	@Override
	public void setDeletable(final boolean deletable) {
		this.deletable = deletable;
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

		final DeletableImpl that = (DeletableImpl) o;

		return deletable == that.deletable;

	}

	@Override
	public int hashCode() {
		return (deletable ? 1 : 0);
	}

}
