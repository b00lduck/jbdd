package com.nigames.jbdd.rest.dto.facet;

public final class CanBeEnabledImpl implements CanBeEnabled {

    private boolean enabled;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CanBeEnabledImpl)) return false;

		CanBeEnabledImpl that = (CanBeEnabledImpl) o;

		if (enabled != that.enabled) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (enabled ? 1 : 0);
	}

}
