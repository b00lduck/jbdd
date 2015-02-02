package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
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
@NamedQueries({
        @NamedQuery(name = CostEntity.NQ_BY_BUYABLE_ID, query = "SELECT c FROM CostEntity c WHERE c.id.buyableId=:buyableId"),
        @NamedQuery(name = CostEntity.NQ_COUNT_BY_BUYABLE_ID, query = "SELECT COUNT(id.buyableId) FROM CostEntity WHERE id.buyableId=:buyableId")
})
public class CostEntity extends AbstractBuyableSubItemEntity {

	public static final String NQ_COUNT_BY_BUYABLE_ID = "CostEntity.0";
	public static final String NQ_BY_BUYABLE_ID = "CostEntity.1";

    /**
     * The embedded primary key.
     */
    @EmbeddedId
    private CostEntityPK id = new CostEntityPK();

    /**
     * The {@link com.nigames.jbdd.domain.entities.item.AbstractItemEntity}.
     */
    @MapsId("buyableId")
    @JoinColumn(name = "buyable_id", referencedColumnName = "id", updatable = false, insertable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private BuyableEntityFacetImpl buyableFacet;

    /**
     * The {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("goodId")
    @JoinColumn(name = "good_id", referencedColumnName = "id", updatable = false, insertable = false)
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
