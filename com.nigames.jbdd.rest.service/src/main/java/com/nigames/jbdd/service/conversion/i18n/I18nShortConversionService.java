package com.nigames.jbdd.service.conversion.i18n;

import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity;
import org.springframework.stereotype.Service;

@Service
public class I18nShortConversionService extends AbstractI18nConversionService<I18nShortEntity> {

    @Override
    public I18nShortEntity getNewEntityInstance() {
        return new I18nShortEntity();
    }

}
