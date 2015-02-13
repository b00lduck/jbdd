package com.nigames.jbdd.types

import spock.lang.Specification
import spock.lang.Unroll

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class LimitParamsSpec extends Specification {


    def static MAX_SIZE = 100

    @Unroll
    def "create(#first, #size) -> (#outFirst, #outSize)"() {

        given:

        when:
        def result = LimitParams.create(first, size)

        then:
        result.getFirst() == outFirst
        result.getSize() == outSize

        where:
        first | size         | outFirst | outSize
        null  | null         | 0        | MAX_SIZE
        null  | 0            | 0        | MAX_SIZE
        null  | MAX_SIZE + 1 | 0        | MAX_SIZE
        null  | MAX_SIZE - 1 | 0        | 99
        0     | null         | 0        | MAX_SIZE
        0     | 0            | 0        | MAX_SIZE
        0     | MAX_SIZE + 1 | 0        | MAX_SIZE
        0     | MAX_SIZE - 1 | 0        | 99
        666   | null         | 666      | MAX_SIZE
        666   | 0            | 666      | MAX_SIZE
        666   | MAX_SIZE + 1 | 666      | MAX_SIZE
        666   | MAX_SIZE - 1 | 666      | 99

    }

}
