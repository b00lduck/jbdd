package com.nigames.jbdd.rest.dto

import com.nigames.jbdd.rest.dto.facet.Buyable
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
class RequirementSpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new Requirement()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter                 | setter                 | testObject    | testType
        "getBuyableId"         | "setBuyableId"         | 42            | long
        "getRequiredBuyableId" | "setRequiredBuyableId" | 52            | long
        "getAmount"            | "setAmount"            | 62            | long
        "getRequiredBuyable"   | "setRequiredBuyable"   | Mock(Buyable) | Buyable.class

    }

    def "testHashCodeAndEquals"() {
        when:
        EqualsVerifier.forClass(Requirement.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();

        then:
        noExceptionThrown()
    }

}
