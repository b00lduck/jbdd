package com.nigames.jbdd.types

import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class ResultListSpec extends Specification {

    def "constructor with list und totalCount"() {

        given:
        def array = new ArrayList<String>()
        array.add("a")
        array.add("b")
        array.add("c")

        when:
        def testSubject = ResultList.create(array, 10)

        then:
        testSubject.getTotalCount() == 10
        testSubject.size() == 3
        noExceptionThrown()
    }

    def "constructor with list"() {

        given:
        def array = new ArrayList<String>()
        array.add("a")
        array.add("b")
        array.add("c")

        when:
        def testSubject = ResultList.create(array)

        then:
        testSubject.getTotalCount() == 3
        testSubject.size() == 3
        noExceptionThrown()
    }

    def "adding fails"() {

        given:
        def array = new ArrayList<String>()
        def testSubject = ResultList.create(array)

        when:
        testSubject.add("a")

        then:
        thrown(UnsupportedOperationException)
    }


}

