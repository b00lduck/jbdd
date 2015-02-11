package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.conversion.dto.PlayerConversionService;
import com.nigames.jbdd.service.conversion.impl.modules.EnabledIdConversionServiceTestModule;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

/**
 * Test for the JobConversionService class.
 *
 * @author Daniel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
public class PlayerConversionServiceTest extends AbstractConversionServiceTest<Player, PlayerEntity> {

    private static final String TEST_NICKNAME = "TechNick";

    @Autowired
    private transient PlayerConversionService playerConversionService;

    @Override
    protected PlayerConversionService getConversionService() {
        return playerConversionService;
    }

    @Override
    protected void addModules() {
        addModule(new EnabledIdConversionServiceTestModule());
    }

    // TODO: Test password
    // TODO: Test num of players
    // TODO: test roles

    @Override
    protected void fillEntity(final PlayerEntity entity) {
        entity.setNickname(TEST_NICKNAME);
    }

    @Override
    protected void checkDto(final Player dto) {
        Assert.isTrue(dto.getNickname().equals(TEST_NICKNAME));
    }

    @Override
    protected void fillDto(final Player dto) {
        dto.setNickname(TEST_NICKNAME);
    }

    @Override
    protected void checkEntity(final PlayerEntity entity) {
        Assert.isTrue(entity.getNickname().equals(TEST_NICKNAME));
    }

}
