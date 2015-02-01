package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;
import com.nigames.jbdd.domain.entities.item.GoodEntity;

public interface BuyableEntityFacet {

	AbstractItemEntity getItem();

    int getScore();

    void setScore(final int score);

    int getBuildtime();

    void setBuildtime(final int buildtime);

	/*

    List<CostEntity> getCostList();

    List<RequirementEntity> getRequirementList();

    List<RequirementEntity> getReferencedRequirements();

    */

    boolean isMulti();

    void setMulti(boolean multi);

    /**
     * Check if a specific Good is in the costList.
     *
     * @param good The Good to be looked for
     * @return true, if the Buyable has the specified good as Cost
     */
    boolean hasCost(GoodEntity good);

}
