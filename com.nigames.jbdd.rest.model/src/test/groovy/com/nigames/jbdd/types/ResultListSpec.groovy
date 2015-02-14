package com.nigames.jbdd.types

import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.UnaryOperator

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class ResultListSpec extends Specification {

    @Unroll
    def "delegate #delegateName to ArrayList"() {

        given:
        def list = Mock(List)
        def testSubject = new ResultList(list, 55)

        when:
        def retVal = testSubject.invokeMethod(delegateName, null)

        then:
        1 * list./$delegateName/() >> ret

        where:
        delegateName   | ret
        "size"         | 20
        "isEmpty"      | true
        "isEmpty"      | false
        "iterator"     | Mock(Iterator)
        "toArray"      | ['1', '2']
        "clear"        | null
        "hashCode"     | 1337
        "spliterator"  | Mock(Spliterator)
        "listIterator" | Mock(ListIterator)

    }

    @Unroll
    def "delegate #delegateName(#params) to ArrayList"() {

        given:
        def list = Mock(List)
        def testSubject = new ResultList(list, 0)

        when:
        def retVal = testSubject.invokeMethod(delegateName, params)

        then:
        1 * list./$delegateName/(params) >> ret
        retVal == ret

        where:
        delegateName   | params              | ret
        "contains"     | [new Object()]      | true
        "toArray"      | [['1', '2']]        | ['3', '4']
        "add"          | [new Object()]      | false
        "remove"       | [new Object()]      | false
        "containsAll"  | [['1', '2']]        | false
        "addAll"       | [['1', '2']]        | false
        "removeAll"    | [['1', '2']]        | false
        "retainAll"    | [['1', '2']]        | false
        "replaceAll"   | Mock(UnaryOperator) | null
        "sort"         | Mock(Comparator)    | null
        "equals"       | new Object()        | true
        "get"          | 1                   | new Object()
        "remove"       | 1                   | new Object()
        "indexOf"      | [new Object()]      | 1
        "lastIndexOf"  | [new Object()]      | 1
        "listIterator" | 1                   | Mock(ListIterator)

    }

    def "delegate subList ( 1, 2) to ArrayList"() {

        given:
        def list = Mock(List)
        def testSubject = new ResultList(list, 0)
        def ret = Mock(List)

        when:
        def retVal = testSubject.subList(1, 2)

        then:
        1 * list.subList(1, 2) >> ret
        retVal == ret

    }

    def "delegate add ( 1, obj) to ArrayList"() {

        given:
        def list = Mock(List)
        def testSubject = new ResultList(list, 0)
        def obj = new Object()

        when:
        def retVal = testSubject.add(1, obj)

        then:
        1 * list.add(1, obj)

    }

    def "delegate set ( 1, obj) to ArrayList"() {

        given:
        def list = Mock(List)
        def testSubject = ResultList.create(list, 0)
        def ret = Mock(List)
        def obj = new Object()

        when:
        def retVal = testSubject.set(1, obj)

        then:
        1 * list.set(1, obj) >> ret
        retVal == ret

    }


    def "delegate addAll ( 1, obj) to ArrayList"() {

        given:
        def list = Mock(List)
        def testSubject = ResultList.create(list)
        def obj = Mock(List)

        when:
        def retVal = testSubject.addAll(1, obj)

        then:
        1 * list.addAll(1, obj) >> true
        retVal == true

    }

    def "return getTotalCount ()"() {

        given:
        def testSubject = ResultList.create(null, 666)

        when:
        def retVal = testSubject.getTotalCount()

        then:
        retVal == 666

    }

}

