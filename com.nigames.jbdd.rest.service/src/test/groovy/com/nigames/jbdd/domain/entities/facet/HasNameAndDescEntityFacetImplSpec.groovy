package com.nigames.jbdd.domain.entities.facet

import com.nigames.jbdd.domain.entities.i18n.I18nLongEntity
import com.nigames.jbdd.domain.entities.i18n.I18nShortEntity
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Modifier

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 22.02.2015.
 */
class HasNameAndDescEntityFacetImplSpec extends Specification {

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new HasNameAndDescEntityFacetImpl()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getModifiers() == Modifier.PUBLIC + Modifier.FINAL
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter           | setter           | testObject            | testType
        "getName"        | "setName"        | new I18nShortEntity() | I18nShortEntity.class
        "getDescription" | "setDescription" | new I18nLongEntity()  | I18nLongEntity.class
    }

    def "default tests"() {
        when:
        def clazz = HasNameAndDescEntityFacetImpl.class
        then:
        FieldTestTools.checkIdAndVersionField(clazz)
        FieldTestTools.checkItemField(clazz)
        FieldTestTools.checkConstructorWithItem(clazz)
    }

}
