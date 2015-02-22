package com.nigames.jbdd.service.service

import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class RandomPasswordGeneratorTest extends Specification {

    def "should create passwords"() {

        given:
        def testSubject = new RandomPasswordGenerator()

        when:
        def pw1 = testSubject.getRandomPassword()
        def pw2 = testSubject.getRandomPassword()

        then:
        pw1 != pw2
        !pw1.equals(pw2)
        pw1.length() >= 5

    }
}
