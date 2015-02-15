package com.nigames.jbdd.service.service.sortParamTransformator;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 07.02.2015.
 */
public class WeightSortParamTransformator implements SortParamTransformator {

	public static final String SORT_FIELD_WEIGHT = "weight";

	public String transform(final String sortParam) {
		if (isResponsible(sortParam)) {
			return "isStorableFacet.weight";
		}
		return sortParam;
	}

	private boolean isResponsible(final String sortParam) {
		return sortParam.equals(SORT_FIELD_WEIGHT);
	}


}
