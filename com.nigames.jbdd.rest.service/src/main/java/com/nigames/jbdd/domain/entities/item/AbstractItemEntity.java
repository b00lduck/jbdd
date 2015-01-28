package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.facet.CanBeEnabledEntityFacet;
import com.nigames.jbdd.domain.entities.facet.HasNameAndDescEntityFacet;
import com.nigames.jbdd.domain.entities.facet.HasNameAndDescEntityFacetImpl;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.i18n.I18n;
import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity;
import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Abstract Database Entity for all Items. Covers {@link com.nigames.jbdd.domain.entities.facet.HasNameAndDescEntityFacet} and {@link com.nigames.jbdd.domain.entities.facet.CanBeEnabledEntityFacet} facet.
 *
 * @author Daniel
 */
@Entity
@Table(name = "item")
public abstract class AbstractItemEntity extends IdentifyableEntityFacetImpl implements CanBeEnabledEntityFacet, HasNameAndDescEntityFacet {

    /**
     * The HasNameAndDesc facet of the item.
     */
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private final HasNameAndDescEntityFacetImpl nameAndDescFacet = new HasNameAndDescEntityFacetImpl();

    /**
     * enabled flag
     */
    private boolean enabled;

    @Override
    public I18n getName() {
        return nameAndDescFacet.getName();
    }

    @Override
    public void setName(final I18nShortEntity name) {
        nameAndDescFacet.setName(name);
    }

    @Override
    public I18n getDescription() {
        return nameAndDescFacet.getDescription();
    }

    @Override
    public void setDescription(final I18nLongEntity description) {
        nameAndDescFacet.setDescription(description);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    // TODO: equals and hashcode
}
