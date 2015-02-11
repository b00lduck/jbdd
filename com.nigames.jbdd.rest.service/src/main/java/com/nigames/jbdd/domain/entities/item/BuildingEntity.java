package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Database Entity for Buildings.
 *
 * @author Daniel
 */
@SuppressWarnings("DuplicateStringLiteralInspection")
@Entity
@Table(name = "ITEM_BUILDING")
@Inheritance(strategy = InheritanceType.JOINED)
public final class BuildingEntity extends AbstractItemEntity implements BuyableEntityFacet {

	/**
	 * The {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet} of this Building.
	 */
	@OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
	private BuyableEntityFacetImpl buyableFacet;

	/**
	 * The jobs offered by this building with according productivity factors (Anderskoeffizient (TM))
	 */
	@OneToMany(mappedBy = "id.buildingId", fetch = FetchType.LAZY)
	@Fetch(FetchMode.SELECT)
	private List<BuildingJobEntity> buildingJobEntity;

	/**
	 * Create instance and setup/link facet instances.
	 */
	public static BuildingEntity newInstance() {
		final BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.buyableFacet = new BuyableEntityFacetImpl(buildingEntity);
		initInstance(buildingEntity);
		return buildingEntity;
	}

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

	/*
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
	*/

	@Override
	public boolean isMulti() {
		return buyableFacet.isMulti();
	}

	@SuppressWarnings("BooleanParameter")
	@Override
	public void setMulti(final boolean multi) {
		buyableFacet.setMulti(multi);
	}

	@Override
	public boolean hasCost(final GoodEntity good) {
		return buyableFacet.hasCost(good);
	}

	// TODO: hashCode, equals and toString

}
