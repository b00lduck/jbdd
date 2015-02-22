package com.nigames.jbdd.domain.entities.i18n

import spock.lang.Specification

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Version

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 22.02.2015.
 */
class AbstractI18nEntitySpec extends Specification {

    def "check id and version field"() {

        given:
        def clazz = AbstractI18nEntity.class

        expect:
        clazz.getDeclaredField("id").getAnnotation(Id) != null
        clazz.getDeclaredField("id").getAnnotation(GeneratedValue) != null
        clazz.getDeclaredField("id").getAnnotation(GeneratedValue).strategy() == GenerationType.AUTO
        clazz.getDeclaredField("version").getAnnotation(Version) != null

    }

    def "id getter"() {

        given:
        def testSubject = Spy(AbstractI18nEntity)
        def field = AbstractI18nEntity.class.getDeclaredField("id")
        field.setAccessible(true)
        field.setLong(testSubject, 42)

        when:
        def res = testSubject.getId()

        then:
        res == 42

    }


    def "getLang()"() {

        given:
        def testSubject = Spy(AbstractI18nEntity)
        def retStr = "return string"

        when:
        def res = testSubject.getLang("de-DE")

        then:
        1 * testSubject.getDe() >> retStr
        0 * testSubject.getEn()
        res == retStr
        noExceptionThrown()

        when:
        res = testSubject.getLang("en-GB")

        then:
        1 * testSubject.getEn() >> retStr
        0 * testSubject.getDe()
        res == retStr
        noExceptionThrown()

        when:
        res = testSubject.getLang("invalid")

        then:
        0 * testSubject.getEn()
        0 * testSubject.getDe()
        res == null
        noExceptionThrown()

    }


    def "setLang()"() {

        given:
        def testSubject = new AbstractI18nEntity() {
            String en = null;
            String de = null;

            @Override
            String getDe() {
                return de
            }

            @Override
            void setDe(String de) {
                this.de = de
            }

            @Override
            String getEn() {
                return en
            }

            @Override
            void setEn(String en) {
                this.en = en
            }

            @Override
            int getMaxLength() {
                return 42
            }
        }

        when:
        testSubject.setLang(lang, "setStr")

        then:
        testSubject.getDe() == de
        testSubject.getEn() == en
        noExceptionThrown()

        where:
        lang    | de       | en
        "de-DE" | "setStr" | null
        "en-GB" | null     | "setStr"
        "inval" | null     | null

    }


    def "longi18n id 1 does not equal shorti18n id 1"() {

        given: "a longi18n and a shorti18n with both ID 1"
        def i18nlong = new I18nLongEntity()
        def i18nshort = new I18nShortEntity()

        def field = AbstractI18nEntity.class.getDeclaredField("id")
        field.setAccessible(true)
        field.setLong(i18nshort, 1L)
        field.setLong(i18nlong, 1L)

        when: "equals is called with each other"
        def res1 = i18nlong.equals(i18nshort)
        def res2 = i18nshort.equals(i18nlong)

        then: "results are false"
        res1 == false
        res2 == false
        noExceptionThrown()
    }

    def "longi18n id 1 has different hashcode than shorti18n id 1"() {

        given: "a good and a player with both ID 1"
        def i18nlong = new I18nLongEntity()
        def i18nshort = new I18nShortEntity()

        def field = AbstractI18nEntity.class.getDeclaredField("id")
        field.setAccessible(true)
        field.setLong(i18nlong, 1L)
        field.setLong(i18nshort, 1L)

        when: "hashcode is called"
        def hash1 = i18nlong.hashCode()
        def hash2 = i18nshort.hashCode()

        then: "results are unequal"
        hash1 != hash2
        noExceptionThrown()
    }


}
