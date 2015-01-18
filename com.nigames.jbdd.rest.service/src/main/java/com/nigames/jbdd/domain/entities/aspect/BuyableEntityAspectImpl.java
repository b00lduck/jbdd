package com.nigames.jbdd.domain.entities.aspect;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract Database Entity for all {@link BuyableEntityAspectImpl} objects (
 * {@link com.nigames.jbdd.domain.entities.item.BuildingEntity} and {@link com.nigames.jbdd.domain.entities.item.TechnologyEntity}).
 *
 * @author Daniel
 */

@Entity
@Table(name = "buyableAspect")
public final class BuyableEntityAspectImpl implements BuyableEntityAspect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version;

    /**
     * Score added by owning one of this {@link BuyableEntityAspectImpl} object.
     */
    private int score;

    /**
     * Time to build this {@link BuyableEntityAspectImpl} object per construction
     * worker.
     */
    private int buildtime;

    /**
     * Describes if this {@link BuyableEntityAspectImpl} object can be bought multiple
     * times. FALSE means, the {@link BuyableEntityAspectImpl} object can only be bought
     * once.
     */
    private boolean multi;

    /**
     * Costs of this {@link BuyableEntityAspectImpl} object represented as a List of
     * {@link CostEntity}.
     */
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    @OneToMany(mappedBy = "id.buyableId", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<CostEntity> costList = new ArrayList<>();

    /**
     * Requirements of this {@link BuyableEntityAspectImpl} object represented as a List
     * of {@link RequirementEntity} .
     */
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    @OneToMany(mappedBy = "id.buyableId", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<RequirementEntity> requirementList = new ArrayList<>();

    /**
     * This is a passive backlink. Gets all {@link BuyableEntityAspectImpl} objects who
     * have this {@link BuyableEntityAspectImpl} object as {@link RequirementEntity}.
     */
    @SuppressWarnings({"FieldMayBeFinal", "CanBeFinal"})
    @OneToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    @JoinColumn(name = "required_buyable_id", updatable = false, insertable = false)
    private List<RequirementEntity> referencedRequirements = new ArrayList<>();

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
