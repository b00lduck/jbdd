package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;

import javax.persistence.*;

/**
 * Database entity of the {@link AbstractBuyableSubItemEntity} Requirement. The data is kept in the
 * composite primary key {@link RequirementEntityPK}.
 *
 * @author Daniel
 */
@Entity
@Table(name = "requirement")
public class RequirementEntity extends AbstractBuyableSubItemEntity {

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
	 * @param id The {@link RequirementEntityPK} to setLang
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
	 * @param buyableFacet The Buyable item to setLang
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
	 * @param requiredBuyableFacet The required buyable item to setLang
	 */
	public void setRequiredBuyableFacet(final BuyableEntityFacetImpl requiredBuyableFacet) {
		this.requiredBuyableFacet = requiredBuyableFacet;
	}

}
