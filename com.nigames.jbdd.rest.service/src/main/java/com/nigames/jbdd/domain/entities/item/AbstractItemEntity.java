package com.nigames.jbdd.domain.entities.item;

import com.nigames.jbdd.domain.entities.aspect.CanBeEnabledEntityAspect;
import com.nigames.jbdd.domain.entities.aspect.HasNameAndDescEntityAspect;
import com.nigames.jbdd.domain.entities.aspect.HasNameAndDescEntityAspectImpl;
import com.nigames.jbdd.domain.entities.aspect.identifyable.IdentifyableEntityAspectImpl;
import com.nigames.jbdd.domain.entities.i18n.I18n;
import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity;
import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Abstract Database Entity for all Items. Covers {@link com.nigames.jbdd.domain.entities.aspect.HasNameAndDescEntityAspect} and {@link com.nigames.jbdd.domain.entities.aspect.CanBeEnabledEntityAspect} aspects.
 *
 * @author Daniel
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "item")
public abstract class AbstractItemEntity extends IdentifyableEntityAspectImpl implements CanBeEnabledEntityAspect, HasNameAndDescEntityAspect {

    /**
     * The HasNameAndDesc aspect of the item.
     */
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private final HasNameAndDescEntityAspectImpl nameAndDesc = new HasNameAndDescEntityAspectImpl();

    /**
     * enabled flag
     */
    private boolean enabled;

    @Override
    public I18n getName() {
        return nameAndDesc.getName();
    }

    @Override
    public void setName(final I18nShortEntity name) {
        nameAndDesc.setName(name);
    }

    @Override
    public I18n getDescription() {
        return nameAndDesc.getDescription();
    }

    @Override
    public void setDescription(final I18nLongEntity description) {
        nameAndDesc.setDescription(description);
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
