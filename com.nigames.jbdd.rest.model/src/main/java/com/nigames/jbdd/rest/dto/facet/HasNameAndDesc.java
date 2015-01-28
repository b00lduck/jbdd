package com.nigames.jbdd.rest.dto.facet;

import java.util.Map;

public interface HasNameAndDesc {

	Map<String, String> getName();

	void setName(final Map<String, String> name);

	Map<String, String> getDescription();

	void setDescription(final Map<String, String> description);

}
