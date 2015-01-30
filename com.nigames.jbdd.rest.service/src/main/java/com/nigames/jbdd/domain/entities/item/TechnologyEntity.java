package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;

import javax.persistence.*;

/**
 * Database entity for Technologies.
 *
 * @author Daniel
 */
@Entity
@Table(name = "ITEM_TECHNOLOGY")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries(@NamedQuery(name = TechnologyEntity.NQ_FIND_ALL_ENABLED_TECHNOLOGIES,
        query = "SELECT t FROM TechnologyEntity t WHERE t.enabled=1"))
public class TechnologyEntity extends AbstractItemEntity implements BuyableEntityFacet {

    public static final String NQ_FIND_ALL_ENABLED_TECHNOLOGIES = "findAllEnabledTechnologies";

	/**
	 * The {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet} of this technology.
	 */
	@OneToOne(mappedBy = "item", cascade = CascadeType.ALL)
	private BuyableEntityFacetImpl buyableFacet;

	/**
	 * Create instance and setup/link facet instances.
	 */
	public static TechnologyEntity newInstance() {
		final TechnologyEntity technologyEntity = new TechnologyEntity();
		technologyEntity.buyableFacet = new BuyableEntityFacetImpl(technologyEntity);
		initInstance(technologyEntity);
		return technologyEntity;
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
}
