package com.nigames.jbdd.domain.entities.aspect;

import com.nigames.jbdd.domain.entities.i18n.I18n;
import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity;
import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity;

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by zerlettd on 19.12.2014.
 */
public interface HasNameAndDescEntityAspect {

    I18n getName();

    void setName(final I18nShortEntity name);

    I18n getDescription();

    void setDescription(final I18nLongEntity description);

}
