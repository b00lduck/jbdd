package com.nigames.jbdd.domain.entities.i18n;

/**
 * Interface for the I18n Entity classes.
 *
 * @author Daniel
 */
public interface I18n {

    String getDe();

    void setDe(final String de);

    String getEn();

    void setEn(final String en);

    int getMaxLength();

	String getLang(String lang);

	void setLang(String lang, String value);

}
