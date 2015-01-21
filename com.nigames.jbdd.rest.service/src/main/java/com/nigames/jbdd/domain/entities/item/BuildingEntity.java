package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.aspect.BuyableEntityAspect;
import com.nigames.jbdd.domain.entities.aspect.BuyableEntityAspectImpl;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.ProductionEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Database Entity for Buildings.
 *
 * @author Daniel
 */
@Entity
@Table(name = "building")
@NamedQueries(
        @NamedQuery(name = BuildingEntity.NQ_FIND_ALL_ENABLED_BUILDINGS,
                query = "SELECT b FROM BuildingEntity b WHERE b.enabled=1")
)
public class BuildingEntity extends AbstractItemEntity implements BuyableEntityAspect {

    @SuppressWarnings("HardCodedStringLiteral")
    public static final String NQ_FIND_ALL_ENABLED_BUILDINGS = "findAllEnabledBuildings";

    /**
     * The {@link com.nigames.jbdd.domain.entities.aspect.BuyableEntityAspect} aspect of this Building.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private final BuyableEntityAspectImpl isBuyable = new BuyableEntityAspectImpl();

    /**
     * The goods produced and consumed by this building as a list of {@link ProductionEntity}.
     */
    @OneToMany(mappedBy = "id.buildingId", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<ProductionEntity> productionList = new ArrayList<>();

    /**
     * The {@link JobEntity} offered by this Building.
     */
    @OneToOne(optional = true)
    private JobEntity job;

    @Override
    public int getScore() {
        return isBuyable.getScore();
    }

    @Override
    public void setScore(final int score) {
        isBuyable.setScore(score);
    }

    @Override
    public int getBuildtime() {
        return isBuyable.getBuildtime();
    }

    @Override
    public void setBuildtime(final int buildtime) {
        isBuyable.setBuildtime(buildtime);
    }

    @Override
    public List<CostEntity> getCostList() {
        return isBuyable.getCostList();
    }

    @Override
    public List<RequirementEntity> getRequirementList() {
        return isBuyable.getRequirementList();
    }

    @Override
    public List<RequirementEntity> getReferencedRequirements() {
        return isBuyable.getReferencedRequirements();
    }

    @Override
    public boolean isMulti() {
        return isBuyable.isMulti();
    }

    @Override
    public void setMulti(final boolean multi) {
        isBuyable.setMulti(multi);
    }

    @Override
    public boolean hasCost(final GoodEntity good) {
        return isBuyable.hasCost(good);
    }

    public List<ProductionEntity> getProductionList() {
        return productionList;
    }

    public void setProductionList(final List<ProductionEntity> productionList) {
        this.productionList = productionList;
    }

    public JobEntity getJob() {
        return job;
    }

    public void setJob(final JobEntity job) {
        this.job = job;
    }

    // TODO: hashCode, equals and toString

}
