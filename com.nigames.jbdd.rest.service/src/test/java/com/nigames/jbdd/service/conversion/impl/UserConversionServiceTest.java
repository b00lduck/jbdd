package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.conversion.dto.UserConversionService;
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
public class UserConversionServiceTest extends
        AbstractConversionServiceTest<User, UserEntity> {

    private static final String TEST_USERNAME = "TestUsername";
    private static final String TEST_EMAIL = "test@test.test";

    @Autowired
    private transient UserConversionService userConversionService;

    @Override
    protected UserConversionService getConversionService() {
        return userConversionService;
    }

    @Override
    protected void addModules() {
        addModule(new EnabledIdConversionServiceTestModule());
    }

    @Override
    protected void fillEntity(final UserEntity entity) {
        entity.setUsername(TEST_USERNAME);
        entity.setEmail(TEST_EMAIL);
    }

    @Override
    protected void checkDto(final User dto) {
        Assert.isTrue(dto.getUsername().equals(TEST_USERNAME));
        Assert.isTrue(dto.getEmail().equals(TEST_EMAIL));
    }

    @Override
    protected void fillDto(final User dto) {
        dto.setUsername(TEST_USERNAME);
        dto.setEmail(TEST_EMAIL);
    }

    @Override
    protected void checkEntity(final UserEntity entity) {
        Assert.isTrue(entity.getUsername().equals(TEST_USERNAME));
        Assert.isTrue(entity.getEmail().equals(TEST_EMAIL));
    }

}
