package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.types.LimitParams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Meta {

	private final long totalItems;

	private final long first;

	private final long size;

	public Meta() {
		totalItems = 0;
		first = 0;
		size = 0;
	}

	private Meta(long first, long size, long totalItems) {
		this.first = first;
		this.size = size;
		this.totalItems = totalItems;
	}

	/**
     * Create the {@link com.nigames.jbdd.rest.dto.Meta} DTO for a {@link com.nigames.jbdd.rest.dto.DtoList}.
     *
     * @param total Total number of items
     * @param limitParams limit parameters
     * @return Meta object with the given values
     */
    @SuppressWarnings("ReuseOfLocalVariable")
    public static Meta create(final long total, final LimitParams limitParams) {

        Long fixedSize = limitParams.getSize();
	    final Long first = limitParams.getFirst();

	    final Long numResults = total - first;
	    if (numResults < fixedSize) {
		    fixedSize = numResults;
	    }

	    return new Meta(first, fixedSize, total);

    }

	public Long getTotalItems() {
		return totalItems;
	}

	public Long getFirst() {
		return first;
	}

	public Long getSize() {
		return size;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Meta)) return false;

		Meta meta = (Meta) o;

		if (first != meta.first) return false;
		if (size != meta.size) return false;
		if (totalItems != meta.totalItems) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (totalItems ^ (totalItems >>> 32));
		result = 31 * result + (int) (first ^ (first >>> 32));
		result = 31 * result + (int) (size ^ (size >>> 32));
		return result;
	}

}
