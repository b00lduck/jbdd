package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Database entity of the {@link AbstractBuyableSubItemEntity} Requirement. The data is kept in the
 * composite primary key {@link RequirementEntityPK}.
 *
 * @author Daniel
 */
@Entity
@Table(name = "requirement")
@NamedQueries({
		@NamedQuery(name = RequirementEntity.NQ_BY_BUYABLE_ID, query = "SELECT r FROM RequirementEntity r WHERE r.id.buyableId=:buyableId"),
		@NamedQuery(name = RequirementEntity.NQ_COUNT_BY_BUYABLE_ID, query = "SELECT COUNT(id.buyableId) FROM RequirementEntity WHERE id.buyableId=:buyableId")
})
public class RequirementEntity extends AbstractBuyableSubItemEntity {

	public static final String NQ_COUNT_BY_BUYABLE_ID = "RequirementEntity.0";
	public static final String NQ_BY_BUYABLE_ID = "RequirementEntity.1";

	/**
	 * The embedded primary key.
	 */
	@EmbeddedId
	private RequirementEntityPK id = new RequirementEntityPK();

	/**
	 * The {@link com.nigames.jbdd.domain.entities.item.AbstractItemEntity}.
	 */
	@MapsId("buyableId")
	@JoinColumn(name = "buyable_id", referencedColumnName = "id", updatable = false, insertable = false)
	@ManyToOne(fetch = FetchType.LAZY)
	private BuyableEntityFacetImpl buyableFacet;

	/**
	 * The {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@MapsId("requiredBuyableId")
	@JoinColumn(name = "required_buyable_id", referencedColumnName = "id", updatable = false, insertable = false)
	private BuyableEntityFacetImpl requiredBuyableFacet;

	/**
	 * @return Get {@link CostEntity#id}
	 */
	public RequirementEntityPK getId() {
		return id;
	}

	/**
	 * @param id The {@link RequirementEntityPK} to set
	 */
	public void setId(final RequirementEntityPK id) {
		this.id = id;
	}

	/**
	 * @return the Buyable item
	 */
	public BuyableEntityFacet getBuyableFacet() {
		return buyableFacet;
	}

	/**
	 * @param buyableFacet The Buyable item to set
	 */
	public void setBuyableFacet(final BuyableEntityFacetImpl buyableFacet) {
		this.buyableFacet = buyableFacet;
	}

	/**
	 * Get the required buyable item.
	 *
	 * @return the required buyable item.
	 */
	public BuyableEntityFacet getRequiredBuyableFacet() {
		return requiredBuyableFacet;
	}

	/**
	 * Set the required buyable item.
	 *
	 * @param requiredBuyableFacet The required buyable item to set
	 */
	public void setRequiredBuyableFacet(final BuyableEntityFacetImpl requiredBuyableFacet) {
		this.requiredBuyableFacet = requiredBuyableFacet;
	}

}
