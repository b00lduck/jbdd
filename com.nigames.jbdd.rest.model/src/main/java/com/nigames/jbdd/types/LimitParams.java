package com.nigames.jbdd.types;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 26.12.2014.
 */
public final class LimitParams {

    private final Long first;
    private final Long size;

    private LimitParams(final Long first, final Long size) {
        this.first = first;
        this.size = size;
    }

    public static LimitParams create(final Long first, final Long size) {
        return new LimitParams(first, size);
    }

    public static LimitParams createDefault() {
        return new LimitParams(null, null);
    }

    public Long getFirst() {
        return first;
    }

    public Long getSize() {
        return size;
    }

}
