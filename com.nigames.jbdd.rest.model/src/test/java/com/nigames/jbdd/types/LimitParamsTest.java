package com.nigames.jbdd.types;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class LimitParamsTest {

    @Test
    public void testSizeParamNotZero() throws Exception {
        final LimitParams testSubject = LimitParams.create(0L, 0L);

        assertThat("Size param cannot be zero", testSubject.getSize(), not(equalTo(0)));
    }
}