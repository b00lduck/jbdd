package com.nigames.jbdd.domain.entities.facet

import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl
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

    def "default tests"() {
        when:
        def clazz = BuyableEntityFacetImpl.class
        then:
        FieldTestTools.checkIdAndVersionField(clazz)
        FieldTestTools.checkItemField(clazz)
        FieldTestTools.checkConstructorWithItem(clazz)
    }

    def "hasCost"() {

        given: "assume Good is contained in list"
        def good1 = new GoodEntity()
        def field = IdentifyableEntityFacetImpl.class.getDeclaredField("id")
        field.setAccessible(true)
        field.setLong(good1, 1L)
        def good2 = new GoodEntity()
        field.setLong(good2, 2L)
        def good3 = new GoodEntity()
        field.setLong(good2, 3L)

        def constEntities = [new CostEntity(good: good1), new CostEntity(good: good3)]

        when:
        def testSubject = new BuyableEntityFacetImpl(costList: constEntities)

        then:
        testSubject.hasCost(good1)
        !testSubject.hasCost(good2)
        testSubject.hasCost(good3)
    }
}
