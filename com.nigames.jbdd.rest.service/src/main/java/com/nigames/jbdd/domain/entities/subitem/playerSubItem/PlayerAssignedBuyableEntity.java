package com.nigames.jbdd.domain.entities.subitem.playerSubItem;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * Abstract Database entity for Players Buyables. This represents the buyables a a game character
 * owns.
 *
 * @author Daniel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "player_buyable")
public class PlayerAssignedBuyableEntity extends AbstractPlayerAssignedEntity {

    /**
     * The owned {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl}.
     *
    @NotNull
    @ManyToOne
    private BuyableEntityFacetImpl buyable;
	*/
    /**
     * The remaining build time.
     */
    private Integer remainbuildtime;

    /**
     * Protected constructor to prevent instantiation.
     */
    protected PlayerAssignedBuyableEntity() {
        // nothing to do here.
    }

	/*
    public BuyableEntityFacetImpl getBuyableFacet() {
        return buyable;
    }

    public void setBuyableFacet(final BuyableEntityFacetImpl buyable) {
        this.buyable = buyable;
    }
    */

    public Integer getRemainbuildtime() {
        return remainbuildtime;
    }

    public void setRemainbuildtime(final Integer remainbuildtime) {
        this.remainbuildtime = remainbuildtime;
    }

}
