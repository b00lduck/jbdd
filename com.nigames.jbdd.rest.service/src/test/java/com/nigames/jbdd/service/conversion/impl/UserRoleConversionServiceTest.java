package com.nigames.jbdd.service.conversion.impl;

import com.nigames.jbdd.domain.entities.auth.UserRoleEntity;
import com.nigames.jbdd.rest.dto.UserRole;
import com.nigames.jbdd.service.config.TestApplicationConfig;
import com.nigames.jbdd.service.conversion.dto.UserRoleConversionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.Assert;

import java.lang.reflect.Field;

/**
 * Test for the JobConversionService class.
 *
 * @author Daniel
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestApplicationConfig.class)
@WebAppConfiguration
public class UserRoleConversionServiceTest extends
        AbstractConversionServiceTest<UserRole, UserRoleEntity> {

    private static final long TEST_ID = 42L;
    private static final String TEST_ROLE_NAME = "TEST_ROLE";

    @Autowired
    private transient UserRoleConversionService userRoleConversionService;

    @Override
    protected UserRoleConversionService getConversionService() {
        return userRoleConversionService;
    }

    @Override
    protected void addModules() {
    }

    @Override
    protected void fillEntity(final UserRoleEntity entity) {

        // Set private field ID of the entity via reflection
        try {
            final Class clazz = UserRoleEntity.class;
            final Field field = clazz.getDeclaredField("id");
            field.setAccessible(true);
            field.set(entity, TEST_ID);
        } catch (Exception e) {
            org.junit.Assert.fail();
        }
        entity.setRole(TEST_ROLE_NAME);
    }

    @Override
    protected void checkDto(final UserRole dto) {
        Assert.isTrue(dto.getId() == TEST_ID);
        Assert.isTrue(dto.getRoleName().equals(TEST_ROLE_NAME));
    }

    @Override
    protected void fillDto(final UserRole dto) {
    }

    @Override
    protected void checkEntity(final UserRoleEntity entity) {
    }

}
