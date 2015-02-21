package com.nigames.jbdd.service.conversion.impl.modules;

import com.nigames.jbdd.domain.entities.facet.CanBeEnabledEntityFacet;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;
import com.nigames.jbdd.rest.dto.facet.CanBeEnabled;
import com.nigames.jbdd.rest.dto.facet.Identifiable;
import com.nigames.jbdd.rest.dto.facet.IsDto;
import org.junit.Assert;

import java.lang.reflect.Field;

public class EnabledIdConversionServiceTestModule implements ConversionServiceTestModuleInterface {

    private static final boolean TEST_ENABLED = true;
    private static final long TEST_ID = 42L;

    @SuppressWarnings("unchecked")
    @Override
    public void fillEntity(final Object entity) {

        // Set private field ID of the entity via reflection
        try {
            final Class clazz = IdentifyableEntityFacetImpl.class;
            final Field field = clazz.getDeclaredField("id");
            field.setAccessible(true);
            field.set(entity, TEST_ID);
        } catch (Exception e) {
            Assert.fail();
        }

        ((CanBeEnabledEntityFacet) entity).setEnabled(TEST_ENABLED);
    }

    @Override
    public void checkDto(final IsDto dto) {
        Assert.assertEquals(TEST_ENABLED, ((CanBeEnabled) dto).isEnabled());
        Assert.assertEquals(TEST_ID, ((Identifiable) dto).getId());
    }

    @Override
    public void fillDto(final IsDto dto) {
        ((Identifiable) dto).setId(TEST_ID);
        ((CanBeEnabled) dto).setEnabled(TEST_ENABLED);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void checkEntity(final Object entity) {
        Assert.assertEquals(TEST_ENABLED, ((CanBeEnabledEntityFacet) entity).isEnabled());
        Assert.assertEquals(0L, ((IdentifyableEntityFacet) entity).getId());
    }

}
