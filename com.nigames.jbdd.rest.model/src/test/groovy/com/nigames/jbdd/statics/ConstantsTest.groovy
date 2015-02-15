package com.nigames.jbdd.statics

import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class ConstantsTest extends Specification {

    def "private constructor"() {

        when:
        def ret = new Constants()

        then:
        ret != null

    }

}
