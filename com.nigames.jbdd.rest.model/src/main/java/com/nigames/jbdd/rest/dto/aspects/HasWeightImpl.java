package com.nigames.jbdd.rest.dto.aspects;

public class HasWeightImpl implements HasWeight {

    private int weight;

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void setWeight(final int weight) {
        this.weight = weight;
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

        final HasWeightImpl hasWeight = (HasWeightImpl) o;

        return weight == hasWeight.weight;

    }

    @Override
    public int hashCode() {
        return weight;
    }

}
