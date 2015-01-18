package com.nigames.jbdd.domain.entities.i18n;

import com.nigames.jbdd.domain.entities.aspect.identifyable.IdentifyableEntityAspect;

import javax.annotation.Nullable;
import javax.persistence.*;

/**
 * Abstract mapped superclass for the I18n interface.
 */
@MappedSuperclass
public abstract class AbstractI18nEntity implements IdentifyableEntityAspect, I18n {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version;

    @Override
    public long getId() {
        return id;
    }

    /**
     * Get by language tag.
     *
     * @param lang Language tag i.e. "de-DE"
     * @return String in given language
     */
    @Override
    @Nullable
    public String get(final String lang) {
        switch (lang) {
            case "de-DE":
                return getDe();
            case "en-GB":
                return getEn();
            default:
                return null;
        }
    }

    /**
     * Set by language tag.
     *
     * @param lang  Language tag i.e. "de-DE"
     * @param value String to set
     */
    @Override
    public void set(final String lang, final String value) {
        if ("de-DE".equals(lang)) {
            setDe(value);
        } else if ("en-GB".equals(lang)) {
            setEn(value);
        }
    }

    // TODO: equals, hashcode and toString

}