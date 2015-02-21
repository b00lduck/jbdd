package com.nigames.jbdd.domain.entities.facet

import com.nigames.jbdd.domain.entities.PlayerEntity
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl
import com.nigames.jbdd.domain.entities.item.AbstractItemEntity
import com.nigames.jbdd.domain.entities.item.GoodEntity
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Modifier

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 20.02.2015.
 */
class BuyableEntityFacetImplSpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new BuyableEntityFacetImpl()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getModifiers() == Modifier.PUBLIC + Modifier.FINAL
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter          | setter          | testObject         | testType
        "getScore"      | "setScore"      | 42                 | int
        "getBuildtime"  | "setBuildtime"  | 42                 | int
        "isMulti"       | "setMulti"      | true               | boolean
    }

    def "getItem"() {

        given: "BuyableEntityFacetImpl with injected AbstractItemEntity"
        def mockedEntity = Mock(AbstractItemEntity)

        when: "default constructor is called"
        def testSubject = new BuyableEntityFacetImpl(mockedEntity)

        then: "getItem will return a mocked entity"
        testSubject.getItem() == mockedEntity
    }

    def "hasCost"() {

        given: "assume Good is contained in list"
        def good1 = new GoodEntity()
        def field = IdentifyableEntityFacetImpl.class.getDeclaredField("id")
        field.setAccessible(true)
        field.setLong(good1, 1L)

        def constEntities = [new CostEntity(good: good1)]

        when:
        def testSubject = new BuyableEntityFacetImpl(costList: constEntities)

        then:
        testSubject.hasCost(good1)
    }
}
