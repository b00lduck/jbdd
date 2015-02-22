package com.nigames.jbdd.domain.entities.facet.identifyable

import com.nigames.jbdd.domain.entities.PlayerEntity
import com.nigames.jbdd.domain.entities.item.GoodEntity
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 20.02.2015.
 */
class IdentifyableEntityFacetImplSpec extends Specification {


    def "id getter"() {

        when:
        def testSubject = new IdentifyableEntityFacetImpl() {

            @Override
            protected boolean isEqual(Object object) {
                return false
            }
        }

        def idField = IdentifyableEntityFacetImpl.class.getDeclaredField("id")
        idField.setAccessible(true)
        idField.setLong(testSubject, 42)

        then:
        testSubject.getId() == 42

    }

    def "player id 1 does not equal good id 1"() {

        given: "a good and a player with both ID 1"
        def player1 = new PlayerEntity()
        def good1 = new GoodEntity()

        def field = IdentifyableEntityFacetImpl.class.getDeclaredField("id")
        field.setAccessible(true)
        field.setLong(good1, 1L)
        field.setLong(player1, 1L)

        when: "equals is called with each other"
        def res1 = player1.equals(good1)
        def res2 = good1.equals(player1)

        then: "results are false"
        res1 == false
        res2 == false
        noExceptionThrown()
    }

    def "player id 1 has different hashcode than good id 1"() {

        given: "a good and a player with both ID 1"
        def player1 = new PlayerEntity()
        def good1 = new GoodEntity()

        def field = IdentifyableEntityFacetImpl.class.getDeclaredField("id")
        field.setAccessible(true)
        field.setLong(good1, 1L)
        field.setLong(player1, 1L)

        when: "hashcode is called"
        def hash1 = player1.hashCode()
        def hash2 = good1.hashCode()

        then: "results are unequal"
        hash1 != hash2
        noExceptionThrown()
    }


}

