package com.nigames.jbdd.service.service.sortParamTransformator;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 07.02.2015.
 */
public class WeightSortParamTransformator implements SortParamTransformator {

	public static final String SORT_FIELD_WEIGHT = "weight";

	public boolean isResponsible(final String sortParam) {
		return sortParam.equals(SORT_FIELD_WEIGHT);
	}

	public String transform(final String sortParam) {
		return "isStorableFacet.weight";
	}

}
