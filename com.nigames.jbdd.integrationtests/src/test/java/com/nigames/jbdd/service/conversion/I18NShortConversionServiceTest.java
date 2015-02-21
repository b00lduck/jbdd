package com.nigames.jbdd.service.conversion;

import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity;
import com.nigames.jbdd.service.conversion.i18n.AbstractI18nConversionService;
import com.nigames.jbdd.service.conversion.i18n.I18nShortConversionService;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test for the I18nConversionService class.
 *
 * @author Daniel
 */
@RunWith(JUnit4.class)
public class I18NShortConversionServiceTest extends AbstractI18nConversionServiceTest {

    private I18nShortConversionService i18NShortConversionService = new I18nShortConversionService();

    @Override
    protected AbstractI18nConversionService<I18nShortEntity> getConversionService() {
        return i18NShortConversionService;
    }

}
