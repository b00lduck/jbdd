package com.nigames.jbdd.rest.dto.facet;

import java.util.HashMap;
import java.util.Map;

public class HasNameAndDescImpl implements HasNameAndDesc {

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
    public boolean equals(final Object o) {
	    if (this == o) {
		    return true;
	    }
	    if (!(o instanceof HasNameAndDescImpl)) {
		    return false;
	    }

	    final HasNameAndDescImpl that = (HasNameAndDescImpl) o;

	    if (!description.equals(that.description)) {
		    return false;
	    }
	    return name.equals(that.name);

    }

	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = (31 * result) + description.hashCode();
		return result;
	}
}
