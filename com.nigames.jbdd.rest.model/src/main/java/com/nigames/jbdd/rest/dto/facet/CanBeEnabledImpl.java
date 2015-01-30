package com.nigames.jbdd.rest.dto.facet;

public class CanBeEnabledImpl implements CanBeEnabled {

    private boolean enabled;

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
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

        final CanBeEnabledImpl that = (CanBeEnabledImpl) o;

        return enabled == that.enabled;
    }

    @Override
    public int hashCode() {
        return (enabled ? 1 : 0);
    }

}
