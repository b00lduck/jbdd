package com.nigames.jbdd.service.service.sortParamTransformator;

import com.nigames.jbdd.statics.Languages;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 07.02.2015.
 */
public class NameSortParamTransformator implements SortParamTransformator {

	public String transform(final String sortParam) {
		if (isResponsible(sortParam)) {
			return "nameAndDescFacet.name." + Languages.tagToDbTag(sortParam.substring(5));
		}
		return sortParam;
	}

	private boolean isResponsible(final String sortParam) {
		return sortParam.startsWith("name.");
	}


}
