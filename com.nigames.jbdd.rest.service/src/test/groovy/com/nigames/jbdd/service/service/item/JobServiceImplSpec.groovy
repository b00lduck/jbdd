package com.nigames.jbdd.service.service.item

import com.nigames.jbdd.domain.entities.item.JobEntity
import com.nigames.jbdd.rest.dto.Good
import com.nigames.jbdd.rest.dto.Job
import com.nigames.jbdd.rest.dto.Production
import com.nigames.jbdd.service.conversion.dto.JobConversionService
import com.nigames.jbdd.service.repository.JobRepository
import com.nigames.jbdd.service.service.sortParamTransformator.NameSortParamTransformator
import com.nigames.jbdd.service.service.subitem.ProductionService
import com.nigames.jbdd.types.ResultList
import org.springframework.stereotype.Service
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class JobServiceImplSpec extends Specification {

    def "findAllEnabled"() {

        given:
        def jobRepository = Mock(JobRepository)
        def jobConversionService = Mock(JobConversionService)

        def testSubject = new JobServiceImpl(
                jobRepository: jobRepository,
                jobConversionService: jobConversionService)

        def jobEntityList = new ArrayList<JobEntity>()
        def jobList = new ArrayList<Job>()

        when:
        def result = testSubject.findAllEnabled()

        then:
        1 * jobRepository.findByEnabled(true) >> jobEntityList
        1 * jobConversionService.convertToDto(jobEntityList) >> jobList
        result == jobList
        testSubject.getRepository() == jobRepository
        testSubject.getConversionService() == jobConversionService
    }

    def "Sort filters initialized"() {

        given:
        def testSubject = Spy(JobServiceImpl)

        when:
        testSubject.afterPropertiesSet()

        then:
        1 * testSubject.addSortParamTransformator(_ as NameSortParamTransformator)

    }

    def "Is a @Service"() {
        expect:
        JobServiceImpl.annotations[0].annotationType() == Service
        JobServiceImpl.annotations.size() == 1
    }

    def "getAddableProductionGoods should return all goods not already added as production"() {

        given:
        def goodService = Mock(GoodService)
        def productionService = Mock(ProductionService)
        def service = new JobServiceImpl(
                goodService: goodService,
                productionService: productionService)
        def jobId = 5

        def goodList = new ArrayList([new Good(id: 1), new Good(id: 2), new Good(id: 3)])
        def productionList = new ArrayList([new Production(goodId: 1)])
        def resultList = new ResultList<Production>(productionList, 1)

        when:
        def result = service.getAddableProductionGoods(jobId)

        then:
        1 * goodService.findAllEnabled() >> goodList
        1 * productionService.findByJobId(jobId) >> resultList

        result.size() == 2
        result.get(0) == goodList[1]
        result.get(1) == goodList[2]

    }


}
