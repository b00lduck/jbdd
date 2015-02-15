package com.nigames.jbdd.rest.dto

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
class TechnologySpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new Technology()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter           | setter           | testObject | testType
        "isEnabled"      | "setEnabled"     | true       | boolean
        "getName"        | "setName"        | Mock(Map)  | Map.class
        "getDescription" | "setDescription" | Mock(Map)  | Map.class
        "getId"          | "setId"          | 52         | long
        "getBuildtime"   | "setBuildtime"   | 52         | int
        "isDeletable"    | "setDeletable"   | true       | boolean
    }

    def "testHashCodeAndEquals"() {
        when:
        EqualsVerifier.forClass(Technology.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();

        then:
        noExceptionThrown()
    }

}
