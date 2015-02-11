package com.nigames.jbdd;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

/**
 * Common methods used in tests.
 */
public abstract class AbstractTest {

    /**
     * Tests {@link Object#equals(Object)} and {@link Object#hashCode()}.
     *
     * @param clazz Class to be tested
     * @param <T> type of Class
     */
    protected <T> void testHashCodeAndEquals(final Class<T> clazz) {
        EqualsVerifier.forClass(clazz)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();
    }
}
