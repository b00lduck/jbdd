package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.conversion.dto.GoodConversionService;
import com.nigames.jbdd.service.conversion.impl.modules.EnabledIdConversionServiceTestModule;
import com.nigames.jbdd.service.conversion.impl.modules.NameDescConversionServiceTestModule;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test for the GoodConversionService class.
 *
 * @author Daniel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
public class GoodConversionServiceTest extends AbstractConversionServiceTest<Good, GoodEntity> {

    @Autowired
    private transient GoodConversionService GoodConversionService;

    @Override
    protected GoodConversionService getConversionService() {
        return GoodConversionService;
    }

    @Override
    protected void addModules() {
        addModule(new NameDescConversionServiceTestModule());
        addModule(new EnabledIdConversionServiceTestModule());
    }

    @Override
    protected void fillEntity(final GoodEntity entity) {
        entity.setWeight(222);
    }

    @Override
    protected void checkDto(final Good dto) {
        Assert.assertEquals(222, dto.getWeight());
    }

    @Override
    protected void fillDto(final Good dto) {
        dto.setWeight(666);
    }

    @Override
    protected void checkEntity(final GoodEntity entity) {
        Assert.assertEquals(666, entity.getWeight());
    }

}
