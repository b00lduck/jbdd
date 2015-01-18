package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.domain.entities.item.TechnologyEntity;
import com.nigames.jbdd.rest.dto.Technology;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.conversion.dto.AbstractConversionService;
import com.nigames.jbdd.service.conversion.dto.TechnologyConversionService;
import com.nigames.jbdd.service.conversion.impl.modules.EnabledIdConversionServiceTestModule;
import com.nigames.jbdd.service.conversion.impl.modules.NameDescConversionServiceTestModule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test for the TechnologyConversionService class.
 *
 * @author Daniel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
public class TechnologyConversionServiceTest extends
        AbstractConversionServiceTest<Technology, TechnologyEntity> {

    @Autowired
    private transient TechnologyConversionService technologyConversionService;

    @Override
    protected AbstractConversionService<TechnologyEntity, Technology> getConversionService() {
        return technologyConversionService;
    }

    @Override
    protected void addModules() {
        addModule(new NameDescConversionServiceTestModule());
        addModule(new EnabledIdConversionServiceTestModule());
    }

    @Override
    protected void fillEntity(final TechnologyEntity entity) {
    }

    @Override
    protected void checkDto(final Technology dto) {
    }

    @Override
    protected void fillDto(final Technology dto) {
    }

    @Override
    protected void checkEntity(final TechnologyEntity entity) {
    }

}
