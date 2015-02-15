package com.nigames.jbdd.service.service.sortParamTransformator

import spock.lang.Specification
import spock.lang.Unroll

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class WeightSortParamTransformatorSpec extends Specification {

    @Unroll
    def "transforms #input to #output"() {

        when:
        def testSubject = new WeightSortParamTransformator()

        then:
        testSubject.transform(input).equals(output)

        where:
        input        | output
        "weight"     | "isStorableFacet.weight"
        "name.de-DE" | "name.de-DE"
        "foo"        | "foo"
    }

}
