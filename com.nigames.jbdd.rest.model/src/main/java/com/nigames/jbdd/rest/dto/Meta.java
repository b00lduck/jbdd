package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.types.LimitParams;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Meta {

	private Long totalItems;

	private Long first;

	private Long size;

	/**
     * Create the {@link com.nigames.jbdd.rest.dto.Meta} DTO for a {@link com.nigames.jbdd.rest.dto.DtoList}.
     *
     * @param total Total number of items
     * @param limitParams limit parameters
     * @return Meta object with the given values
     */
    @SuppressWarnings("ReuseOfLocalVariable")
	public static Meta create(final Long total, final LimitParams limitParams) {

        Long fixedSize = limitParams.getSize();
	    Long fixedFirst = limitParams.getFirst();

        final Meta ret = new Meta();

        if ((null == fixedSize) || (null == fixedFirst)) {

            if (null == fixedSize) {
                fixedSize = 0L;
            }

            if (null == fixedFirst) {
                fixedFirst = 0L;
            }

            ret.setSize(fixedSize);
        } else {
            final Long numResults = total - fixedFirst;
            if (numResults < fixedSize) {
                fixedSize = numResults;
            }
	        if (0 > fixedSize) {
		        fixedSize = 0L;
            }
            ret.setSize(fixedSize);
        }
        ret.setTotalItems(total);
        ret.setFirst(fixedFirst);

        return ret;

    }

	public Long getTotalItems() {
		return totalItems;
	}

	private void setTotalItems(final Long totalItems) {
		this.totalItems = totalItems;
	}

	public Long getFirst() {
		return first;
	}

	private void setFirst(final Long first) {
		this.first = first;
	}

	public Long getSize() {
		return size;
	}

	private void setSize(final Long size) {
		this.size = size;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Meta)) return false;

		Meta meta = (Meta) o;

		if (first != null ? !first.equals(meta.first) : meta.first != null) return false;
		if (size != null ? !size.equals(meta.size) : meta.size != null) return false;
		if (totalItems != null ? !totalItems.equals(meta.totalItems) : meta.totalItems != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = totalItems != null ? totalItems.hashCode() : 0;
		result = 31 * result + (first != null ? first.hashCode() : 0);
		result = 31 * result + (size != null ? size.hashCode() : 0);
		return result;
	}
}
