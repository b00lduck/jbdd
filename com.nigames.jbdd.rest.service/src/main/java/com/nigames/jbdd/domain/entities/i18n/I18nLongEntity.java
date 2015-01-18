package com.nigames.jbdd.domain.entities.i18n;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Database entity for I18nLong. Holds long internationalized text fragments used as descriptions in
 * Items.
 *
 * @author Daniel
 */
@Entity
@Table(name = "i18n_long")
public final class I18nLongEntity extends AbstractI18nEntity {

    /**
     * maximum length of the text fields in entity I18nLong.
     */
    private static final int MAXLENGTH = 5000;

    /**
     * German text.
     */
    @Size(max = MAXLENGTH)
    private String de;

    /**
     * English text.
     */
    @Size(max = MAXLENGTH)
    private String en;

    @Override
    public int getMaxLength() {
        return MAXLENGTH;
    }

    @Override
    public String getDe() {
        return de;
    }

    @Override
    public void setDe(final String de) {
        this.de = de;
    }

    @Override
    public String getEn() {
        return en;
    }

    @Override
    public void setEn(final String en) {
        this.en = en;
    }

}
