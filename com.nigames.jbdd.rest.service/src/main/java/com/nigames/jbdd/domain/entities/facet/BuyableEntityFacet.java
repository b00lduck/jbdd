package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;

import java.util.List;

public interface BuyableEntityFacet {

    int getScore();

    void setScore(final int score);

    int getBuildtime();

    void setBuildtime(final int buildtime);

    List<CostEntity> getCostList();

    List<RequirementEntity> getRequirementList();

    List<RequirementEntity> getReferencedRequirements();

    boolean isMulti();

    void setMulti(final boolean multi);

    /**
     * Check if a specific Good is in the costList.
     *
     * @param good The Good to be looked for
     * @return true, if the Buyable has the specified good as Cost
     */
    boolean hasCost(final GoodEntity good);

}
