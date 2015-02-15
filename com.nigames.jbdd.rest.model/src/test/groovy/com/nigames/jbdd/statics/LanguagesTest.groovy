package com.nigames.jbdd.statics

import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class LanguagesTest extends Specification {

    def "testGetLocaleList"() {
        when:
        def ret = Languages.getLocaleList()

        then:
        ret[0].equals(new Locale("de", "de"))
        ret[0].toString().equals('de_DE')
        ret[1].equals(new Locale("en", "gb"))
        ret[1].toString().equals('en_GB')
    }

    def "testGetLanguageTagList"() {
        when:
        def ret = Languages.getLanguageTagList()

        then:
        ret[0].equals('de-DE')
        ret[1].equals('en-GB')
    }

    def "testGetLanguageDbTagList"() {
        when:
        def ret = Languages.getLanguageDbTagList()

        then:
        ret[0].equals('de')
        ret[1].equals('en')
    }

    def "testTagToDbTag()"() {
        when:
        def ret = Languages.tagToDbTag(tag)

        then:
        ret == dbTag

        where:
        tag       | dbTag
        "de-DE"   | "de"
        "en-GB"   | "en"
        "invalid" | null
    }

    def "private constructor"() {

        when:
        def ret = new Languages()

        then:
        ret != null

    }

}
