package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;

public interface BuyableEntityFacet {

	AbstractItemEntity getItem();

    int getScore();

    void setScore(final int score);

    int getBuildtime();

    void setBuildtime(final int buildtime);

    boolean isMulti();

    void setMulti(boolean multi);

}
