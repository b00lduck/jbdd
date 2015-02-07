package com.nigames.jbdd.types;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 26.12.2014.
 */
public final class LimitParams {

	private static final long MAX_SIZE = 100L;

	private final long first;
	private final long size;

    private LimitParams(final Long first, final Long size) {
	    this.first = first;
	    this.size = size;
    }

    public static LimitParams create(final Long first, final Long size) {
	    return new LimitParams(fixFirst(first), fixSize(size));
    }

	private static Long fixSize(Long size) {
		final Long fixedSize;

		if ((null == size) || (0L == size)) {
			fixedSize = MAX_SIZE;
		} else {
			fixedSize = size;
		}
		return fixedSize;
	}

	private static Long fixFirst(Long first) {
		final Long fixedFirst;

		if (null == first) {
			fixedFirst = 0L;
		} else {
			fixedFirst = first;
		}
		return fixedFirst;
	}

	public static LimitParams createDefault() {
		return new LimitParams(0L, MAX_SIZE);
	}

	public long getFirst() {
		return first;
    }

	public long getSize() {
		return size;
    }

}
