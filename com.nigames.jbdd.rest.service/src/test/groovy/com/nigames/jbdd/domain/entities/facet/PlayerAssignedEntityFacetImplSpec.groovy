package com.nigames.jbdd.domain.entities.facet

import com.nigames.jbdd.domain.entities.PlayerEntity
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Modifier

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 22.02.2015.
 */
class PlayerAssignedEntityFacetImplSpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new PlayerAssignedEntityFacetImpl()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getModifiers() == Modifier.PUBLIC + Modifier.FINAL
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter      | setter      | testObject         | testType
        "getPlayer" | "setPlayer" | new PlayerEntity() | PlayerEntity.class
    }

}
