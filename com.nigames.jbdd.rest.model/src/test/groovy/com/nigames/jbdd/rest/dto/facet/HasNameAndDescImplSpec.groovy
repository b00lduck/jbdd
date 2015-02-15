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
class HasNameAndDescImplSpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new HasNameAndDescImpl()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter           | setter           | testObject                    | testType
        "getName"        | "setName"        | new HashMap<String, String>() | Map.class
        "getDescription" | "setDescription" | new HashMap<String, String>() | Map.class

    }

    def "testHashCodeAndEquals"() {
        when:
        EqualsVerifier.forClass(HasNameAndDescImpl.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();

        then:
        noExceptionThrown()
    }

}
