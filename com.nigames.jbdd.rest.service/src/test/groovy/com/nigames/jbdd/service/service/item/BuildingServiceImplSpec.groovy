package com.nigames.jbdd.service.service.item

import com.nigames.jbdd.domain.entities.item.BuildingEntity
import com.nigames.jbdd.rest.dto.Building
import com.nigames.jbdd.service.conversion.dto.BuildingConversionService
import com.nigames.jbdd.service.repository.BuildingRepository
import com.nigames.jbdd.service.service.sortParamTransformator.NameSortParamTransformator
import org.springframework.stereotype.Service
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class BuildingServiceImplSpec extends Specification {

    def "findAllEnabled"() {

        given:
        def buildingRepository = Mock(BuildingRepository)
        def buildingConversionService = Mock(BuildingConversionService)

        def testSubject = new BuildingServiceImpl(
                buildingRepository: buildingRepository,
                buildingConversionService: buildingConversionService)

        def buildingEntityList = new ArrayList<BuildingEntity>()
        def buildingList = new ArrayList<Building>()

        when:
        def result = testSubject.findAllEnabled()

        then:
        1 * buildingRepository.findByEnabled(true) >> buildingEntityList
        1 * buildingConversionService.convertToDto(buildingEntityList) >> buildingList
        result == buildingList
        testSubject.getRepository() == buildingRepository
        testSubject.getConversionService() == buildingConversionService
    }

    def "Sort filters initialized"() {

        given:
        def testSubject = Spy(BuildingServiceImpl)

        when:
        testSubject.afterPropertiesSet()

        then:
        1 * testSubject.addSortParamTransformator(_ as NameSortParamTransformator)

    }

    def "Is a @Service"() {

        expect:
        BuildingServiceImpl.annotations[0].annotationType() == Service
        BuildingServiceImpl.annotations.size() == 1

    }


}
