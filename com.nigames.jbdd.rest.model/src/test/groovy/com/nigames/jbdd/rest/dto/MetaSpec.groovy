package com.nigames.jbdd.rest.dto

import com.nigames.jbdd.types.LimitParams
import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class MetaSpec extends Specification {

    def "default constructor"() {

        when:
        def testSubject = new Meta()

        then:
        testSubject.getTotalItems() == 0
        testSubject.getSize() == 0
        testSubject.getFirst() == 0
    }

    def "create"() {

        when:
        def limitParams = LimitParams.create(first, size)
        def testSubject = Meta.create(total, limitParams)

        then:
        testSubject.getTotalItems() == outTotal
        testSubject.getSize() == outSize
        testSubject.getFirst() == outFirst

        where:
        first | size | total | outFirst | outSize | outTotal
        null  | null | 0     | 0        | 0       | 0
        0     | null | 0     | 0        | 0       | 0
        null  | 0    | 0     | 0        | 0       | 0
        null  | null | 42    | 0        | 42      | 42
        0     | null | 42    | 0        | 42      | 42
        null  | 0    | 42    | 0        | 42      | 42
        null  | null | 101   | 0        | 100     | 101
        0     | null | 101   | 0        | 100     | 101
        null  | 0    | 101   | 0        | 100     | 101

    }

    def "testHashCodeAndEquals"() {
        when:
        EqualsVerifier.forClass(Meta.class)
                .suppress(Warning.NONFINAL_FIELDS)
                .verify();

        then:
        noExceptionThrown()
    }

}
