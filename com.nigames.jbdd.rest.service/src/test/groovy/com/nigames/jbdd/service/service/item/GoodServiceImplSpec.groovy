package com.nigames.jbdd.service.service.item

import com.nigames.jbdd.domain.entities.item.GoodEntity
import com.nigames.jbdd.rest.dto.Good
import com.nigames.jbdd.service.conversion.dto.GoodConversionService
import com.nigames.jbdd.service.repository.GoodRepository
import com.nigames.jbdd.service.service.sortParamTransformator.NameSortParamTransformator
import com.nigames.jbdd.service.service.sortParamTransformator.WeightSortParamTransformator
import org.springframework.stereotype.Service
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class GoodServiceImplSpec extends Specification {

    def "findAllEnabled"() {

        given:
        def goodRepository = Mock(GoodRepository)
        def goodConversionService = Mock(GoodConversionService)

        def testSubject = new GoodServiceImpl(
                goodRepository: goodRepository,
                goodConversionService: goodConversionService)

        def goodEntityList = new ArrayList<GoodEntity>()
        def goodList = new ArrayList<Good>()

        when:
        def result = testSubject.findAllEnabled()

        then:
        1 * goodRepository.findByEnabled(true) >> goodEntityList
        1 * goodConversionService.convertToDto(goodEntityList) >> goodList
        result == goodList
        testSubject.getRepository() == goodRepository
        testSubject.getConversionService() == goodConversionService
    }

    def "Sort filters initialized"() {

        given:
        def testSubject = Spy(GoodServiceImpl)

        when:
        testSubject.afterPropertiesSet()

        then:
        1 * testSubject.addSortParamTransformator(_ as NameSortParamTransformator)
        1 * testSubject.addSortParamTransformator(_ as WeightSortParamTransformator)

    }

    def "Is a @Service"() {

        expect:
        GoodServiceImpl.annotations[0].annotationType() == Service
        GoodServiceImpl.annotations.size() == 1

    }


}
