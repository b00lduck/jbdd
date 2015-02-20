package com.nigames.jbdd.domain.entities.facet.identifyable

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 20.02.2015.
 */
class IdentifyableEntityFacetImplSpec extends Specification {

    def "equals and hashcode contract"() {
        expect:
        EqualsVerifier.forClass(IdentifyableEntityFacetImpl)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify()
    }


    def "id getter"() {

        when:
        def testSubject = new IdentifyableEntityFacetImpl() {}

        def idField = IdentifyableEntityFacetImpl.class.getDeclaredField("id")
        idField.setAccessible(true)
        idField.setLong(testSubject, 42)

        then:
        testSubject.getId() == 42

    }

}
