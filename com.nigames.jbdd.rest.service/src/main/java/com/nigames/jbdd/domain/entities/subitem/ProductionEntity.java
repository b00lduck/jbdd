package com.nigames.jbdd.domain.entities.subitem;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.domain.entities.item.JobEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.AbstractAmountSubItemEntity;

import javax.persistence.*;

/**
 * Database entity of the Production of jobs. The data is kept in the
 * composite primary key {@link com.nigames.jbdd.domain.entities.subitem.ProductionEntityPK}.
 *
 * @author Daniel
 */
@Entity
@Table(name = "production")
public class ProductionEntity extends AbstractAmountSubItemEntity {

	/**
	 * The embedded primary key.
	 */
	@EmbeddedId
	private ProductionEntityPK id = new ProductionEntityPK();

	/**
	 * The {@link com.nigames.jbdd.domain.entities.item.JobEntity}.
	 */
	@MapsId("jobId")
	@JoinColumn(name = "job", referencedColumnName = "id", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private JobEntity job;

	/**
	 * The {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("goodId")
	@JoinColumn(name = "good", referencedColumnName = "id", updatable = false, insertable = false)
	private GoodEntity good;

	/**
	 * @return Get {@link com.nigames.jbdd.domain.entities.subitem.ProductionEntity#id}
	 */
	public ProductionEntityPK getId() {
		return id;
	}

	/**
	 * @param id The {@link com.nigames.jbdd.domain.entities.subitem.ProductionEntity#id} to set
	 */
	public void setId(final ProductionEntityPK id) {
		this.id = id;
	}

	/**
	 * @return Get the Job item
	 */
	public JobEntity getJob() {
		return job;
	}

	/**
	 * @param job The Job item to set
	 */
	public void setJob(final JobEntity job) {
		this.job = job;
	}

	/**
	 * @return Get the Good item
	 */
	public GoodEntity getGood() {
		return good;
	}

	/**
	 * @param good The Good item to set
	 */
	public void setGood(final GoodEntity good) {
		this.good = good;
	}

}
