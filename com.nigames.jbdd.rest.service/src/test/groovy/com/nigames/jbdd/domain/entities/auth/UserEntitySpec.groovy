package com.nigames.jbdd.domain.entities.auth

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Modifier

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 20.02.2015.
 */
class UserEntitySpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new UserEntity()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter        | setter        | testObject    | testType
        "isEnabled"   | "setEnabled"  | true          | boolean
        "getUsername" | "setUsername" | "graf_hardt"  | String.class
        "getPassword" | "setPassword" | "password"    | String.class
        "getEmail"    | "setEmail"    | "a@test.test" | String.class

    }

    @Unroll
    def "constructor works and is public"() {

        when:
        def testSubject = new UserEntity()

        then:
        testSubject.getUserRoleList() instanceof ArrayList
        testSubject.getUserRoleList().size() == 0

        testSubject.getPlayerList() instanceof ArrayList
        testSubject.getPlayerList().size() == 0

        testSubject.getClass().getConstructor().getModifiers() == Modifier.PUBLIC

    }

    def "toString returns String"() {
        when:
        def testSubject = new UserEntity()

        then:
        testSubject.toString().length() > 5
        noExceptionThrown()
    }

    def "equals and hashcode contract"() {
        expect:
        EqualsVerifier.forClass(UserEntity)
                .withRedefinedSuperclass()
                .verify()
    }

}
