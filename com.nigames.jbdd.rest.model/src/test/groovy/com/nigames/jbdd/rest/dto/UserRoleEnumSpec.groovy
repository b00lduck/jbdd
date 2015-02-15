package com.nigames.jbdd.rest.dto

import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class UserRoleEnumSpec extends Specification {

    def "count"() {

        when:
        def values = UserRoleEnum.values()

        then:
        values.length == 12

    }

}
