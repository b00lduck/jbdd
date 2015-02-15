package com.nigames.jbdd.rest.dto.facet;

import java.util.HashMap;
import java.util.Map;

public final class HasNameAndDescImpl implements HasNameAndDesc {

	private Map<String, String> name = new HashMap<>();

	private Map<String, String> description = new HashMap<>();

	@Override
	public Map<String, String> getName() {
        return name;
    }

    @Override
    public void setName(final Map<String, String> name) {
        this.name = name;
    }

    @Override
    public Map<String, String> getDescription() {
        return description;
    }

    @Override
    public void setDescription(final Map<String, String> description) {
        this.description = description;
    }

    @SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")

    @Override
    public boolean equals(Object o) {
	    if (this == o) return true;
	    if (!(o instanceof HasNameAndDescImpl)) return false;

	    HasNameAndDescImpl that = (HasNameAndDescImpl) o;

	    if (description != null ? !description.equals(that.description) : that.description != null) return false;
	    if (name != null ? !name.equals(that.name) : that.name != null) return false;

	    return true;
    }

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		return result;
	}

}
