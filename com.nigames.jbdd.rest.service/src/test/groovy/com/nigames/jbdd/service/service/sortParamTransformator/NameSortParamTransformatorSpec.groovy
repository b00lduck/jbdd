package com.nigames.jbdd.service.service.sortParamTransformator

import spock.lang.Specification
import spock.lang.Unroll

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class NameSortParamTransformatorSpec extends Specification {

    @Unroll
    def "transforms #input to #output"() {

        when:
        def testSubject = new NameSortParamTransformator()

        then:
        testSubject.transform(input).equals(output)

        where:
        input        | output
        "name.de-DE" | "nameAndDescFacet.name.de"
        "name.en-GB" | "nameAndDescFacet.name.en"
        "foo"        | "foo"
        "weight"     | "weight"
    }
}
