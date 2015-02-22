package com.nigames.jbdd.service.service.subitem

import com.nigames.jbdd.domain.entities.subitem.ProductionEntity
import com.nigames.jbdd.domain.entities.subitem.ProductionEntityPK
import com.nigames.jbdd.rest.dto.Production
import com.nigames.jbdd.service.conversion.dto.ProductionConversionService
import com.nigames.jbdd.service.repository.ProductionRepository
import com.nigames.jbdd.types.LimitParams
import com.nigames.jbdd.types.SortParams
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import spock.lang.Specification


/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 22.02.2015.
 */
class ProductionServiceImplSpec extends Specification {

    def "abstract getter"() {
        given:
        def productionRepository = Mock(ProductionRepository)
        def productionConversionService = Mock(ProductionConversionService)

        when:
        def testSubject = new ProductionServiceImpl(
                productionRepository: productionRepository,
                productionConversionService: productionConversionService)

        then:
        testSubject.getRepository() == productionRepository
        testSubject.getConversionService() == productionConversionService
        noExceptionThrown()
    }

    def "update"() {

        given:
        def productionRepository = Mock(ProductionRepository)
        def productionConversionService = Mock(ProductionConversionService)

        def testSubject = new ProductionServiceImpl(
                productionRepository: productionRepository,
                productionConversionService: productionConversionService)

        def dto = new Production(goodId: 42, jobId: 13)
        def productionEntity = new ProductionEntity()

        when:
        def result = testSubject.update(dto)

        then:
        1 * productionRepository.findOne(new ProductionEntityPK(13, 42)) >> productionEntity
        1 * productionConversionService.convertToDto(productionEntity) >> dto
        result == dto
        noExceptionThrown()
    }

    def "delete"() {

        given:
        def productionRepository = Mock(ProductionRepository)

        def testSubject = new ProductionServiceImpl(
                productionRepository: productionRepository)

        when:
        def result = testSubject.delete(13, 42)

        then:
        1 * productionRepository.delete(new ProductionEntityPK(13, 42))
        noExceptionThrown()
    }

    def "findByJobId"() {

        given:
        def productionRepository = Mock(ProductionRepository)
        def productionConversionService = Mock(ProductionConversionService)

        def testSubject = new ProductionServiceImpl(
                productionRepository: productionRepository,
                productionConversionService: productionConversionService)

        def dtoList = new ArrayList<Production>()
        def productionEntityList = new ArrayList<ProductionEntity>()

        when:
        def result = testSubject.findByJobId(13)

        then:
        1 * productionRepository.findByIdJobId(13) >> productionEntityList;
        1 * productionConversionService.convertToDto(productionEntityList) >> dtoList
        result == dtoList
        noExceptionThrown()
    }

    def "findByJobId paged"() {

        given:
        def testSubject = Spy(ProductionServiceImpl)

        // inject "productionRepository"
        def productionRepository = Mock(ProductionRepository)
        def field1 = ProductionServiceImpl.class.getDeclaredField("productionRepository")
        field1.setAccessible(true)
        field1.set(testSubject, productionRepository)

        // inject "productionConversionService"
        def productionConversionService = Mock(ProductionConversionService)
        def field2 = ProductionServiceImpl.class.getDeclaredField("productionConversionService")
        field2.setAccessible(true)
        field2.set(testSubject, productionConversionService)

        def dtoList = new ArrayList<Production>()
        def productionEntityList = new ArrayList<ProductionEntity>()
        def limitParams = LimitParams.createDefault()
        def sortParams = SortParams.createDefault()

        def pageable = Mock(Pageable)
        def page = Mock(Page)

        when:
        def result = testSubject.findByJobId(13, limitParams, sortParams)

        then:
        1 * testSubject.findByJobId(13, limitParams, sortParams) >> {
            callRealMethodWithArgs(13, limitParams, sortParams)
        }
        1 * testSubject.createPageable(limitParams, sortParams) >> pageable
        1 * productionRepository.findByIdJobId(13, pageable) >> page;
        1 * page.getContent() >> productionEntityList
        1 * productionConversionService.convertToDto(productionEntityList) >> dtoList
        result == dtoList
        noExceptionThrown()
    }

}
