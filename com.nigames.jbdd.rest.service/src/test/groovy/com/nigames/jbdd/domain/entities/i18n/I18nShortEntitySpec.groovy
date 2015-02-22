package com.nigames.jbdd.domain.entities.i18n

import nl.jqno.equalsverifier.EqualsVerifier
import spock.lang.Specification
import spock.lang.Unroll

import java.lang.reflect.Modifier

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 22.02.2015.
 */
class I18nShortEntitySpec extends Specification {

    def "equals and hashcode contract"() {
        expect:
        EqualsVerifier.forClass(I18nShortEntity)
                .withRedefinedSuperclass()
                .verify()
    }

    @Unroll
    def "#getter/#setter works and is public"() {

        given:
        def testSubject = new I18nShortEntity()

        when:
        testSubject.invokeMethod(setter, testObject)

        then:
        testSubject.invokeMethod(getter, null) == testObject
        testSubject.getClass().getModifiers() == Modifier.PUBLIC + Modifier.FINAL
        testSubject.getClass().getMethod(getter).getModifiers() == Modifier.PUBLIC
        testSubject.getClass().getMethod(setter, testType).getModifiers() == Modifier.PUBLIC

        where:
        getter  | setter  | testObject   | testType
        "getDe" | "setDe" | "teststring" | String.class
        "getEn" | "setEn" | "teststring" | String.class
    }

    def "getMaxLength"() {
        expect:
        new I18nShortEntity().getMaxLength() == 40
    }

}
