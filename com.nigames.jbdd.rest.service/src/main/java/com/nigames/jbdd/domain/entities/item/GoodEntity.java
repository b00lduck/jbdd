package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.facet.IsStorableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.IsStorableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.ProductionEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database entity for Good.
 *
 * @author Daniel
 */
@SuppressWarnings({"HardCodedStringLiteral", "StringConcatenation", "DuplicateStringLiteralInspection"})
@Entity
@Table(name = "ITEM_GOOD")
@Inheritance(strategy = InheritanceType.JOINED)
public class GoodEntity extends AbstractItemEntity implements IsStorableEntityFacet {

	/**
	 * This is a passive backlink. Gets all {@link CostEntity} objects who use this Good.
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "good_id", updatable = false, insertable = false)
	private final List<CostEntity> referencedCosts = new ArrayList<>();
	/**
	 * This is a passive backlink. Gets all {@link ProductionEntity} objects who use this Good.
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "good_id", updatable = false, insertable = false)
	private final List<ProductionEntity> referencedProductions = new ArrayList<>();

	@OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
	private IsStorableEntityFacetImpl isStorableFacet;

	/**
	 * Create instance and setup/link facet instances.
	 */
	public static GoodEntity newInstance() {
		final GoodEntity goodEntity = new GoodEntity();
		//noinspection AccessingNonPublicFieldOfAnotherObject
		goodEntity.isStorableFacet = new IsStorableEntityFacetImpl(goodEntity);
		initInstance(goodEntity);
		return goodEntity;
	}

	public List<CostEntity> getReferencedCosts() {
		return referencedCosts;
	}

	public List<ProductionEntity> getReferencedProductions() {
		return referencedProductions;
	}

	@Override
	public int getWeight() {
		return isStorableFacet.getWeight();
	}

	@Override
	public void setWeight(final int weight) {
		isStorableFacet.setWeight(weight);
	}

	@Override
	public int getDensity() {
		return isStorableFacet.getDensity();
	}

	@Override
	public void setDensity(final int density) {
		isStorableFacet.setDensity(density);
	}

	// TODO: hashCode, equals and toString

}
