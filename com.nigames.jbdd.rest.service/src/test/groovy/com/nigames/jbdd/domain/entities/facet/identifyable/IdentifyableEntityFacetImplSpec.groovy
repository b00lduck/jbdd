package com.nigames.jbdd.domain.entities.facet.identifyable

import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 20.02.2015.
 */
class IdentifyableEntityFacetImplSpec extends Specification {


    def "id getter"() {

        when:
        def testSubject = new IdentifyableEntityFacetImpl() {

            @Override
            protected boolean isEqual(Object object) {
                return false
            }
        }

        def idField = IdentifyableEntityFacetImpl.class.getDeclaredField("id")
        idField.setAccessible(true)
        idField.setLong(testSubject, 42)

        then:
        testSubject.getId() == 42

    }

}
