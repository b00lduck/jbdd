package com.nigames.jbdd.rest.dto

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class MetaSpec extends Specification {

    def "testHashCodeAndEquals"() {
        when:
        EqualsVerifier.forClass(Meta.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();

        then:
        noExceptionThrown()
    }

}
