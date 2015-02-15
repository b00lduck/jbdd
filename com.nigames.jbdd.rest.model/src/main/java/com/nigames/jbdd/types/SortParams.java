package com.nigames.jbdd.types;

import java.util.Collection;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 26.12.2014.
 */
public final class SortParams {

    private final String sort;
	private final boolean desc;

	private SortParams(final String sort, final Boolean desc) {
		this.sort = sort;
		this.desc = desc;
	}

    /**
     * Static builder for sort parameter fixing
     *
     * @param sortParams
     * @param allowedSortFields
     */
    public static SortParams createFixed(final SortParams sortParams, final Collection<String> allowedSortFields) {

        if (!allowedSortFields.contains(sortParams.getSort())) {
	        return new SortParams("id", false);
        }

        return new SortParams(sortParams.getSort(), sortParams.isDesc());

    }

	public static SortParams create(final String sort, final Boolean desc) {
		if (null == desc) {
			return new SortParams(sort, false);
		} else {
			return new SortParams(sort, desc);
		}
	}

    public static SortParams createDefault() {
        return new SortParams(null, false);
    }

    public String getSort() {
        return sort;
    }

	public boolean isDesc() {
	    return desc;
    }


}
