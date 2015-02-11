package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.AbstractTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

import static org.junit.Assert.*;

public class CostEntityPKTest extends AbstractTest {

    @Test
    public void testHashCodeAndEqualsContract() throws Exception {
        testHashCodeAndEquals(CostEntityPK.class);
    }
}