package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
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
public class BuildingEntity extends AbstractItemEntity implements BuyableEntityFacet {

    @SuppressWarnings("HardCodedStringLiteral")
    public static final String NQ_FIND_ALL_ENABLED_BUILDINGS = "findAllEnabledBuildings";

    /**
     * The {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet} facet of this Building.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private final BuyableEntityFacetImpl buyableFacet = new BuyableEntityFacetImpl();

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
        return buyableFacet.getScore();
    }

    @Override
    public void setScore(final int score) {
        buyableFacet.setScore(score);
    }

    @Override
    public int getBuildtime() {
        return buyableFacet.getBuildtime();
    }

    @Override
    public void setBuildtime(final int buildtime) {
        buyableFacet.setBuildtime(buildtime);
    }

    @Override
    public List<CostEntity> getCostList() {
        return buyableFacet.getCostList();
    }

    @Override
    public List<RequirementEntity> getRequirementList() {
        return buyableFacet.getRequirementList();
    }

    @Override
    public List<RequirementEntity> getReferencedRequirements() {
        return buyableFacet.getReferencedRequirements();
    }

    @Override
    public boolean isMulti() {
        return buyableFacet.isMulti();
    }

    @Override
    public void setMulti(final boolean multi) {
        buyableFacet.setMulti(multi);
    }

    @Override
    public boolean hasCost(final GoodEntity good) {
        return buyableFacet.hasCost(good);
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
