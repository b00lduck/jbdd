package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;
import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;

import javax.persistence.*;

/**
 * Abstract Database Entity for all {@link BuyableEntityFacetImpl} objects (
 * {@link com.nigames.jbdd.domain.entities.item.BuildingEntity} and {@link com.nigames.jbdd.domain.entities.item.TechnologyEntity}).
 *
 * @author Daniel
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "FACET_BUYABLE")
public final class BuyableEntityFacetImpl implements BuyableEntityFacet {

	@Id
	private long id;

	@Version
	private int version;

	@JoinColumn(name = "id")
	@MapsId
	@OneToOne
	private AbstractItemEntity item;

    /**
     * Score added by owning one of this {@link BuyableEntityFacetImpl} object.
     */
    private int score;

    /**
     * Time to build this {@link BuyableEntityFacetImpl} object per construction
     * worker.
     */
    private int buildtime;

    /**
     * Describes if this {@link BuyableEntityFacetImpl} object can be bought multiple
     * times. FALSE means, the {@link BuyableEntityFacetImpl} object can only be bought
     * once.
     */
    private boolean multi;

    /**
     * Costs of this {@link BuyableEntityFacetImpl} object represented as a List of
     * {@link CostEntity}.
     *
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    @OneToMany(mappedBy = "id.buyableId", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<CostEntity> costList = new ArrayList<>();

    /**
     * Requirements of this {@link BuyableEntityFacetImpl} object represented as a List
     * of {@link RequirementEntity} .
     *
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    @OneToMany(mappedBy = "id.buyableId", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<RequirementEntity> requirementList = new ArrayList<>();

    /**
     * This is a passive backlink. Gets all {@link BuyableEntityFacetImpl} objects who
     * have this {@link BuyableEntityFacetImpl} object as {@link RequirementEntity}.
     *
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "required_buyable_id", updatable = false, insertable = false)
    private List<RequirementEntity> referencedRequirements = new ArrayList<>();
    */

    private BuyableEntityFacetImpl() {}

	public BuyableEntityFacetImpl(final AbstractItemEntity item) {
		this();
		this.item = item;
	}

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public void setScore(final int score) {
        this.score = score;
    }

    @Override
    public int getBuildtime() {
        return buildtime;
    }

    @Override
    public void setBuildtime(final int buildtime) {
        this.buildtime = buildtime;
    }

	@Override
	public AbstractItemEntity getItem() {
		return item;
	}

	/*

    @Override
    public List<CostEntity> getCostList() {
        return costList;
    }

    @Override
    public List<RequirementEntity> getRequirementList() {
        return requirementList;
    }

    @Override
    public List<RequirementEntity> getReferencedRequirements() {
        return referencedRequirements;
    }

    */

    @Override
    public boolean isMulti() {
        return multi;
    }

    @Override
    public void setMulti(final boolean multi) {
        this.multi = multi;
    }

    @Override
    public boolean hasCost(final GoodEntity good) {
        // TODO: implement
        /*for (final CostEntity cost : costList) {
            if (cost.getGood().getId().equals(good.getId())) {
				return true;
			}
		}
		*/
        return false;
    }

}
