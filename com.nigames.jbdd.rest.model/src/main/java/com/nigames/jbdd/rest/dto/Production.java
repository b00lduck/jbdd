package com.nigames.jbdd.rest.dto;

import com.nigames.jbdd.rest.dto.facet.IsDto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public final class Production implements IsDto {

	private long goodId;

	private Good good;

	private long jobId;

	private long amount;

	public long getGoodId() {
		return goodId;
	}

	public void setGoodId(final long goodId) {
		this.goodId = goodId;
	}

	public long getJobId() {
		return jobId;
	}

	public void setJobId(final long jobId) {
		this.jobId = jobId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(final long amount) {
		this.amount = amount;
	}

	public Good getGood() {
		return good;
	}

	public void setGood(final Good good) {
		this.good = good;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Production)) {
			return false;
		}

		final Production that = (Production) o;

		if (amount != that.amount) {
			return false;
		}
		if (goodId != that.goodId) {
			return false;
		}
		if (jobId != that.jobId) {
			return false;
		}
		return !((null != good) ? !good.equals(that.good) : (null != that.good));

	}

	@Override
	public int hashCode() {
		int result = (int) (goodId ^ (goodId >>> 32));
		result = (31 * result) + ((null != good) ? good.hashCode() : 0);
		result = (31 * result) + (int) (jobId ^ (jobId >>> 32));
		result = (31 * result) + (int) (amount ^ (amount >>> 32));
		return result;
	}

}
