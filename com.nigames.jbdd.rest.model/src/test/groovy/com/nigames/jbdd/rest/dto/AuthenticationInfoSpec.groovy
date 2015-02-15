package com.nigames.jbdd.rest.dto

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class AuthenticationInfoSpec extends Specification {

    def "testSetUsername"() {

        given:
        def testSubject = new AuthenticationInfo()

        when:
        testSubject.setUsername("abc")

        then:
        testSubject.getUsername() == "abc"

    }

    def "testSetRoles"() {

        given:
        def testSubject = new AuthenticationInfo()
        def list = new ArrayList<String>()

        when:
        testSubject.setRoles(list)

        then:
        testSubject.getRoles() == list

    }

    def "testHashCodeAndEquals"() {
        when:
        EqualsVerifier.forClass(AuthenticationInfo.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();

        then:
        noExceptionThrown()
    }

}
