package com.nigames.jbdd.types;

import com.google.common.collect.ForwardingList;

import java.util.Collections;
import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
public class ResultList<DtoType> extends ForwardingList<DtoType> {

    private final List<DtoType> list;

    private final long count;

	private ResultList(final List<DtoType> list, final long aCount) {
		this.list = Collections.unmodifiableList(list);
		count = aCount;
	}

	public static <DtoType> ResultList<DtoType> create(final List<DtoType> list) {
		return create(list, list.size());
    }

    public static <DtoType> ResultList<DtoType> create(final List<DtoType> list, final long total) {
        return new ResultList<>(list, total);
    }

	@Override
	protected List<DtoType> delegate() {
		return list;
	}

    public long getTotalCount() {
        return count;
    }

}
