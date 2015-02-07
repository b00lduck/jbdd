package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.domain.entities.item.GoodEntity;

import javax.persistence.*;

/**
 * Database entity of the {@link AbstractBuyableSubItemEntity} Production. The data is kept in the
 * composite primary key {@link ProductionEntityPK}.
 *
 * @author Daniel
 */
@Entity
@Table(name = "production")
public class ProductionEntity extends AbstractBuyableSubItemEntity {

    /**
     * The embedded primary key.
     */
    @EmbeddedId
    private ProductionEntityPK id = new ProductionEntityPK();

    /**
     * The producing/consuming {@link BuildingEntity}.
     */
    @ManyToOne
    @MapsId("buildingId")
    @JoinColumn(name = "building_id", referencedColumnName = "id")
    private BuildingEntity building;

    /**
     * The produced/consumed {@link com.nigames.jbdd.domain.entities.item.GoodEntity}.
     */
    @ManyToOne
    @MapsId("goodId")
    @JoinColumn(name = "good_id", referencedColumnName = "id")
    private GoodEntity good;

    /**
     * The JPA version field.
     */
    @Version
    private Integer version;

    /**
     * @return Get {@link ProductionEntity#id}
     */
    public ProductionEntityPK getId() {
        return id;
    }

    /**
     * @param id The {@link ProductionEntity#id} to setLang
     */
    public void setId(final ProductionEntityPK id) {
        this.id = id;
    }

    /**
     * @return Get the {@link BuildingEntity}
     */
    public BuildingEntity getBuilding() {
        return building;
    }

    /**
     * @param building The {@link BuildingEntity} to setLang
     */
    public void setBuilding(final BuildingEntity building) {
        this.building = building;
    }

    /**
     * @return Get {@link com.nigames.jbdd.domain.entities.item.GoodEntity}
     */
    public GoodEntity getGood() {
        return good;
    }

    /**
     * @param good The {@link com.nigames.jbdd.domain.entities.item.GoodEntity} to setLang
     */
    public void setGood(final GoodEntity good) {
        this.good = good;
    }

}
