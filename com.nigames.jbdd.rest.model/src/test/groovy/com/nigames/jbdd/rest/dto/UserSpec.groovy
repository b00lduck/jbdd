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
class UserSpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new User()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter          | setter          | testObject     | testType
        "getUsername"   | "setUsername"   | "testUsername" | String.class
        "getPassword"   | "setPassword"   | "testPassword" | String.class
        "getEmail"      | "setEmail"      | "testEmail"    | String.class
        "getRoles"      | "setRoles"      | Mock(List)     | List.class
        "getNumPlayers" | "setNumPlayers" | 42             | int
        "isEnabled"     | "setEnabled"    | true           | boolean
        "isDeletable"   | "setDeletable"  | true           | boolean
        "getId"         | "setId"         | 666            | long
    }

    def "testHashCodeAndEquals"() {
        when:
        EqualsVerifier.forClass(User.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();

        then:
        noExceptionThrown()
    }

}
