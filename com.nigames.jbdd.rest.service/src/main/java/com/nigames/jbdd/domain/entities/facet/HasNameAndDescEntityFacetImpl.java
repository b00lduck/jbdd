package com.nigames.jbdd.domain.entities.facet;

import com.nigames.jbdd.domain.entities.i18n.I18n;
import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity;
import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity;
import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Abstract Database Entity for all Items.
 *
 * @author Daniel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "FACET_NAME_AND_DESC")
public final class HasNameAndDescEntityFacetImpl implements HasNameAndDescEntityFacet {

    @Id
    private long id;

    @Version
    private int version;

	@JoinColumn(name = "id")
	@MapsId
	@OneToOne
	private AbstractItemEntity item;

    /**
     * The name of the item.
     */
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private I18nShortEntity name = new I18nShortEntity();

    /**
     * The description of the item.
     */
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private I18nLongEntity description = new I18nLongEntity();

	private HasNameAndDescEntityFacetImpl() {}

	public HasNameAndDescEntityFacetImpl(final AbstractItemEntity item) {
		this();
		this.item = item;
	}

    @Override
    public I18n getName() {
        return name;
    }

    @Override
    public void setName(final I18nShortEntity name) {
        this.name = name;
    }

    @Override
    public I18n getDescription() {
        return description;
    }

    @Override
    public void setDescription(final I18nLongEntity description) {
        this.description = description;
    }

}
