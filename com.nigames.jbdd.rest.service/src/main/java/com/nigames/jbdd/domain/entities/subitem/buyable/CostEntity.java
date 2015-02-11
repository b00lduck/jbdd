package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.item.GoodEntity;

import javax.persistence.*;

/**
 * Database entity of the {@link AbstractAmountSubItemEntity} Cost. The data is kept in the
 * composite primary key {@link CostEntityPK}.
 *
 * @author Daniel
 */
@Entity
@Table(name = "cost")
public class CostEntity extends AbstractAmountSubItemEntity {

    /**
     * The embedded primary key.
     */
    @EmbeddedId
    private CostEntityPK id = new CostEntityPK();

    /**
     * The {@link com.nigames.jbdd.domain.entities.item.AbstractItemEntity}.
     */
    @MapsId("buyableId")
    @JoinColumn(name = "buyable", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private BuyableEntityFacetImpl buyableFacet;

    /**
     * The {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("goodId")
    @JoinColumn(name = "good", referencedColumnName = "id", updatable = false, insertable = false)
    private GoodEntity good;

    /**
     * @return Get {@link CostEntity#id}
     */
    public CostEntityPK getId() {
        return id;
    }

    /**
     * @param id The {@link CostEntity#id} to setLang
     */
    public void setId(final CostEntityPK id) {
        this.id = id;
    }

    /**
     * @return Get the Buyable item
     */
    public BuyableEntityFacet getBuyableFacet() {
        return buyableFacet;
    }

    /**
     * @param buyableFacet The Buyable item to setLang
     */
    public void setBuyableFacet(final BuyableEntityFacetImpl buyableFacet) {
        this.buyableFacet = buyableFacet;
    }

    /**
     * @return Get the Good item
     */
    public GoodEntity getGood() {
        return good;
    }

    /**
     * @param good The Good item to setLang
     */
    public void setGood(final GoodEntity good) {
        this.good = good;
    }

}
