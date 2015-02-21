package com.nigames.jbdd.domain.entities.subitem.buyable;

import com.nigames.jbdd.AbstractTest;
import org.junit.Test;

public class CostEntityPKTest extends AbstractTest {

    @Test
    public void testHashCodeAndEqualsContract() throws Exception {
        testHashCodeAndEquals(CostEntityPK.class);
    }

}
