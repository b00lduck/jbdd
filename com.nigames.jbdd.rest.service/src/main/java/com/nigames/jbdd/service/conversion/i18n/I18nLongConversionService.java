package com.nigames.jbdd.service.conversion.i18n;

import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity;
import org.springframework.stereotype.Service;

@Service
public class I18nLongConversionService extends AbstractI18nConversionService<I18nLongEntity> {

    @Override
    public I18nLongEntity getNewEntityInstance() {
        return new I18nLongEntity();
    }

}
