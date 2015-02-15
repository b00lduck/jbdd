package com.nigames.jbdd.service.service.item

import com.nigames.jbdd.domain.entities.item.TechnologyEntity
import com.nigames.jbdd.rest.dto.Technology
import com.nigames.jbdd.service.conversion.dto.TechnologyConversionService
import com.nigames.jbdd.service.repository.TechnologyRepository
import com.nigames.jbdd.service.service.sortParamTransformator.NameSortParamTransformator
import org.springframework.stereotype.Service
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class TechnologyServiceImplSpec extends Specification {

    def "findAllEnabled"() {

        given:
        def technologyRepository = Mock(TechnologyRepository)
        def technologyConversionService = Mock(TechnologyConversionService)

        def testSubject = new TechnologyServiceImpl(
                technologyRepository: technologyRepository,
                technologyConversionService: technologyConversionService)

        def technologyEntityList = new ArrayList<TechnologyEntity>()
        def technologyList = new ArrayList<Technology>()

        when:
        def result = testSubject.findAllEnabled()

        then:
        1 * technologyRepository.findByEnabled(true) >> technologyEntityList
        1 * technologyConversionService.convertToDto(technologyEntityList) >> technologyList
        result == technologyList
        testSubject.getRepository() == technologyRepository
        testSubject.getConversionService() == technologyConversionService
    }

    def "Sort filters initialized"() {

        given:
        def testSubject = Spy(TechnologyServiceImpl)

        when:
        testSubject.afterPropertiesSet()

        then:
        1 * testSubject.addSortParamTransformator(_ as NameSortParamTransformator)

    }

    def "Is a @Service"() {

        expect:
        TechnologyServiceImpl.annotations[0].annotationType() == Service
        TechnologyServiceImpl.annotations.size() == 1

    }


}
