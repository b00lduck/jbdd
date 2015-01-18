package com.nigames.jbdd.service.service.querystrategy;

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

    public Long getFirst() {
        return first;
    }

    public Long getSize() {
        return size;
    }

}
