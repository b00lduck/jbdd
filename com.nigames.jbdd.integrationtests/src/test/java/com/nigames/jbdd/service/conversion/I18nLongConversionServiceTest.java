package com.nigames.jbdd.service.conversion;

import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity;
import com.nigames.jbdd.service.conversion.i18n.AbstractI18nConversionService;
import com.nigames.jbdd.service.conversion.i18n.I18nLongConversionService;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test for the I18nLongConversionService class.
 *
 * @author Daniel
 */
@RunWith(JUnit4.class)
public class I18nLongConversionServiceTest extends AbstractI18nConversionServiceTest {

    private I18nLongConversionService i18nLongConversionService = new I18nLongConversionService();

    @Override
    protected AbstractI18nConversionService<I18nLongEntity> getConversionService() {
        return i18nLongConversionService;
    }

}
