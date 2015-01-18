package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.domain.entities.item.StoragetypeEntity;
import com.nigames.jbdd.rest.dto.Storagetype;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.conversion.dto.AbstractConversionService;
import com.nigames.jbdd.service.conversion.dto.StoragetypeConversionService;
import com.nigames.jbdd.service.conversion.impl.modules.EnabledIdConversionServiceTestModule;
import com.nigames.jbdd.service.conversion.impl.modules.NameDescConversionServiceTestModule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test for the StoragetypeConversionService class.
 *
 * @author Daniel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
public class StoragetypeConversionServiceTest extends
        AbstractConversionServiceTest<Storagetype, StoragetypeEntity> {

    @Autowired
    private transient StoragetypeConversionService storagetypeConversionService;

    @Override
    protected AbstractConversionService<StoragetypeEntity, Storagetype> getConversionService() {
        return storagetypeConversionService;
    }

    @Override
    protected void addModules() {
        addModule(new NameDescConversionServiceTestModule());
        addModule(new EnabledIdConversionServiceTestModule());
    }

    @Override
    protected void fillEntity(final StoragetypeEntity entity) {
    }

    @Override
    protected void checkDto(final Storagetype dto) {
    }

    @Override
    protected void fillDto(final Storagetype dto) {
    }

    @Override
    protected void checkEntity(final StoragetypeEntity entity) {
    }

}
