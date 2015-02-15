package com.nigames.jbdd.types

import spock.lang.Specification
import spock.lang.Unroll

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class SortParamsSpec extends Specification {

    @Unroll
    def "create(#sort, #desc) -> (#outSort, #outDesc)"() {

        when:
        def result = SortParams.create(sort, desc)

        then:
        outSort == result.getSort()
        outDesc == result.isDesc()

        where:
        sort        | desc  | outSort     | outDesc
        null        | true  | null        | true
        null        | false | null        | false
        null        | null | null        | false
        "sortField" | true  | "sortField" | true
        "sortField" | false | "sortField" | false
        "sortField" | null | "sortField" | false

    }


    def "createDefault"() {

        when:
        def sp = SortParams.createDefault()

        then:
        sp.getSort() == null
        sp.isDesc() == false
    }


    @Unroll
    def "createFixed testCase \"#testCase\""() {

        when:
        def sp = SortParams.create(sort, desc)
        def result = SortParams.createFixed(sp, list)

        then:
        outSort == result.getSort()
        outDesc == result.isDesc()

        where:
        testCase | sort   | desc  | list             | outSort | outDesc
        "a"      | null   | true  | ['sf_a', 'sf_b'] | "id"    | false
        "b"      | null   | false | ['sf_a', 'sf_b'] | "id"    | false
        "c" | null   | null  | ['sf_a', 'sf_b'] | "id"   | false
        "d" | "sf_a" | true  | ['sf_a', 'sf_b'] | "sf_a" | true
        "e" | "sf_a" | false | ['sf_a', 'sf_b'] | "sf_a" | false
        "f" | "sf_b" | true  | ['sf_a', 'sf_b'] | "sf_b" | true
        "g" | "sf_b" | false | ['sf_a', 'sf_b'] | "sf_b" | false
        "h" | "sf_c" | true  | ['sf_a', 'sf_b'] | "id"   | false
        "i" | "sf_c" | false | ['sf_a', 'sf_b'] | "id"   | false

    }

}
