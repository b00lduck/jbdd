package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.conversion.dto.AbstractConversionService;
import com.nigames.jbdd.service.conversion.dto.BuildingConversionService;
import com.nigames.jbdd.service.conversion.impl.modules.EnabledIdConversionServiceTestModule;
import com.nigames.jbdd.service.conversion.impl.modules.NameDescConversionServiceTestModule;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Test for the BuildingConversionService class.
 *
 * @author Daniel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
public class BuildingConversionServiceTest extends AbstractConversionServiceTest<Building, BuildingEntity> {

    @Autowired
    private BuildingConversionService buildingConversionService;

    @Override
    protected AbstractConversionService<BuildingEntity, Building> getConversionService() {
        return buildingConversionService;
    }

    @Override
    protected void addModules() {
        addModule(new NameDescConversionServiceTestModule());
        addModule(new EnabledIdConversionServiceTestModule());
    }

    @Override
    protected void fillEntity(final BuildingEntity entity) {
    }

    @Override
    protected void checkDto(final Building dto) {
        Assert.assertEquals(true, dto.isDeletable());
    }

    @Override
    protected void fillDto(final Building dto) {
    }

    @Override
    protected void checkEntity(final BuildingEntity entity) {
    }

}
