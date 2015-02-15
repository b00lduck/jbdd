package com.nigames.jbdd.rest.dto

import com.nigames.jbdd.types.LimitParams
import com.nigames.jbdd.types.ResultList
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class DtoListSpec extends Specification {

    def "default constructor"() {

        when:
        def testSubject = new DtoList<Job>()

        then:
        testSubject.getData() != null
        testSubject.getMeta() != null

    }


    def "special constructor"() {

        when:
        def array = new ArrayList<String>()
        def data = new ResultList(array, 5)
        def limitParams = LimitParams.createDefault()
        def testSubject = new DtoList<Job>(data, limitParams)

        then:
        testSubject.getData() == data
        testSubject.getMeta() != null
        testSubject.getMeta().getFirst() == 0
        testSubject.getMeta().getSize() == 5
        testSubject.getMeta().getTotalItems() == 5

    }

}
