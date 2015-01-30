package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
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
@Entity
@Table(name = "ITEM_GOOD")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
		@NamedQuery(name = GoodEntity.NQ_ADDABLE_COST_SORTED_BY_ID, query = GoodEntity.QUERY_ADDABLE_COST + " ORDER BY g.id"),
		@NamedQuery(name = GoodEntity.NQ_ADDABLE_COST_SORTED_BY_ID_DESC, query = GoodEntity.QUERY_ADDABLE_COST + " ORDER BY g.id DESC"),
		@NamedQuery(name = GoodEntity.NQ_ADDABLE_COST_COUNT, query = "SELECT COUNT(id) FROM GoodEntity g " + GoodEntity.QUERY_ADDABLE_COST_WHERE),
})
public class GoodEntity extends AbstractItemEntity implements IsStorableEntityFacet {

	public static final String QUERY_ADDABLE_COST_WHERE = " WHERE g.enabled=1 ";
	public static final String QUERY_ADDABLE_COST = "SELECT g FROM GoodEntity g " + QUERY_ADDABLE_COST_WHERE;

	public static final String NQ_ADDABLE_COST_SORTED_BY_ID = "GoodEntity.1";
	public static final String NQ_ADDABLE_COST_SORTED_BY_ID_DESC = "GoodEntity.2";

	public static final String NQ_ADDABLE_COST_COUNT = "GoodEntity.0";

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
    private IsStorableEntityFacetImpl isStorable;

	private GoodEntity() {}

	/**
	 * Create instance and setup/link facet instances.
	 */
	public static GoodEntity newInstance() {
		GoodEntity goodEntity = new GoodEntity();
		goodEntity.isStorable = new IsStorableEntityFacetImpl(goodEntity);
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
		return isStorable.getWeight();
	}

	@Override
	public void setWeight(final int weight) {
		isStorable.setWeight(weight);
	}

	@Override
	public int getDensity() {
		return isStorable.getDensity();
	}

	@Override
	public void setDensity(final int density) {
		isStorable.setDensity(density);
	}

	// TODO: hashCode, equals and toString

}
