package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.domain.entities.aspect.BuyableEntityAspectImpl;
import com.nigames.jbdd.domain.entities.item.GoodEntity;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

/**
 * Database entity of the {@link AbstractBuyableSubItemEntity} Cost. The data is kept in the
 * composite primary key {@link CostEntityPK}.
 *
 * @author Daniel
 */
@Entity
@Table(name = "cost")
@NamedQueries(@NamedQuery(name = "findCostsByBuyable",
        query = "FROM CostEntity WHERE buyable=:buyable"))
public class CostEntity extends AbstractBuyableSubItemEntity {

    /**
     * The embedded primary key.
     */
    @EmbeddedId
    private CostEntityPK id = new CostEntityPK();

    /**
     * The {@link com.nigames.jbdd.domain.entities.aspect.BuyableEntityAspectImpl}.
     */
    @MapsId("buyableId")
    @JoinColumn(name = "buyable_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BuyableEntityAspectImpl buyable;

    /**
     * The {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("goodId")
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private GoodEntity good;

    /**
     * The JPA version field.
     */
    @Version
    private Integer version;

    /**
     * @return Get {@link CostEntity#id}
     */
    public CostEntityPK getId() {
        return id;
    }

    /**
     * @param id The {@link CostEntity#id} to set
     */
    public void setId(final CostEntityPK id) {
        this.id = id;
    }

    /**
     * @return Get the Buyable item
     */
    public BuyableEntityAspectImpl getBuyable() {
        return buyable;
    }

    /**
     * @param buyable The Buyable item to set
     */
    public void setBuyable(final BuyableEntityAspectImpl buyable) {
        this.buyable = buyable;
    }

    /**
     * @return Get the Good item
     */
    public GoodEntity getGood() {
        return good;
    }

    /**
     * @param good The Good item to set
     */
    public void setGood(final GoodEntity good) {
        this.good = good;
    }

}
