package com.nigames.jbdd.rest.dto.facet

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Modifier

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class DeletableImplSpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new DeletableImpl()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter        | setter         | testObject | testType
        "isDeletable" | "setDeletable" | true       | boolean
        "isDeletable" | "setDeletable" | false      | boolean

    }

    def "testHashCodeAndEquals"() {
        when:
        EqualsVerifier.forClass(DeletableImpl.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();

        then:
        noExceptionThrown()
    }

}
