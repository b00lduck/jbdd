package com.nigames.jbdd.service.service

import com.nigames.jbdd.domain.entities.item.AbstractItemEntity
import com.nigames.jbdd.rest.dto.facet.IsDto
import com.nigames.jbdd.service.conversion.dto.AbstractConversionService
import com.nigames.jbdd.service.rest.exceptionprovider.ContentNotFoundException
import com.nigames.jbdd.service.service.sortParamTransformator.SortParamTransformator
import com.nigames.jbdd.types.LimitParams
import com.nigames.jbdd.types.SortParams
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 15.02.2015.
 */
class AbstractRepositoryBackedServiceSpec extends Specification {

    def "create() should convert and call repository.save"() {

        given:
        def testSubject = Spy(AbstractRepositoryBackedService)
        def dto = Mock(IsDto)
        def retDto = Mock(IsDto)
        def entity = Mock(AbstractItemEntity)
        def repository = Mock(PagingAndSortingRepository)
        def conversionService = Mock(AbstractConversionService)

        when:
        def result = testSubject.create(dto)

        then:
        testSubject.getConversionService() >> conversionService
        testSubject.getRepository() >> repository
        1 * conversionService.convertToEntity(dto) >> entity
        1 * conversionService.convertToDto(entity) >> retDto
        1 * repository.save(entity)
        result == retDto
        noExceptionThrown()
    }

    def "update() should convert and call repository findOne"() {

        given:
        def testSubject = Spy(AbstractRepositoryBackedService)
        def dto = Mock(IsDto)
        def retDto = Mock(IsDto)
        def entity = Mock(AbstractItemEntity)
        def repository = Mock(PagingAndSortingRepository)
        def conversionService = Mock(AbstractConversionService)
        def id = 42

        when:
        def result = testSubject.update(id, dto)

        then:
        testSubject.getConversionService() >> conversionService
        testSubject.getRepository() >> repository
        1 * repository.findOne(id) >> entity
        1 * conversionService.updateEntity(dto, entity)
        1 * conversionService.convertToDto(entity) >> retDto
        result == retDto
        noExceptionThrown()
    }

    def "delete() should call repository.delete"() {

        given:
        def testSubject = Spy(AbstractRepositoryBackedService)
        def repository = Mock(PagingAndSortingRepository)
        def id = 42

        when:
        testSubject.delete(id)

        then:
        testSubject.getRepository() >> repository
        1 * repository.delete(id)
        noExceptionThrown()
    }

    def "findAll() should call repostiory and convert"() {

        given:
        def testSubject = Spy(AbstractRepositoryBackedService)
        def repository = Mock(PagingAndSortingRepository)
        def limitParams = LimitParams.createDefault()
        def sortParams = SortParams.createDefault()
        def conversionService = Mock(AbstractConversionService)
        def page = Mock(Page)
        def dtoList = new ArrayList<?>()

        when:
        def ret = testSubject.findAll(limitParams, sortParams)

        then:
        1 * testSubject.getRepository() >> repository
        1 * repository.findAll(_ as Pageable) >> page
        1 * testSubject.getConversionService() >> conversionService
        1 * conversionService.convertToDto(page.getContent()) >> dtoList

        ret.list == dtoList

    }

    def "findAll() should call repostiory and convert with sort"() {

        given:
        def testSubject = Spy(AbstractRepositoryBackedService)
        def repository = Mock(PagingAndSortingRepository)
        def limitParams = LimitParams.createDefault()
        def sortField = "sort"
        def sortParams = SortParams.create(sortField, true)
        def conversionService = Mock(AbstractConversionService)
        def page = Mock(Page)
        def dtoList = new ArrayList<?>()
        def spt = Mock(SortParamTransformator)
        testSubject.addSortParamTransformator(spt)

        when:
        def ret = testSubject.findAll(limitParams, sortParams)

        then:
        1 * spt.transform(sortField) >> sortField
        1 * testSubject.getRepository() >> repository
        1 * repository.findAll(_ as Pageable) >> page
        1 * testSubject.getConversionService() >> conversionService
        1 * conversionService.convertToDto(page.getContent()) >> dtoList

        ret.list == dtoList

    }


    def "findById() should call repostiory and convert"() {

        given:
        def testSubject = Spy(AbstractRepositoryBackedService)
        def repository = Mock(PagingAndSortingRepository)
        def conversionService = Mock(AbstractConversionService)
        def id = 5
        def dto = Mock(IsDto)
        def entity = Mock(AbstractItemEntity)
        testSubject.getRepository() >> repository
        testSubject.getConversionService() >> conversionService

        when:
        def ret = testSubject.findById(id)

        then:
        1 * repository.findOne(id) >> entity
        1 * conversionService.convertToDto(entity) >> dto

        ret == dto

    }


    def "findById() should throw ContentNotFoundException"() {

        given:
        def testSubject = Spy(AbstractRepositoryBackedService)
        def repository = Mock(PagingAndSortingRepository)
        def id = 5

        testSubject.getRepository() >> repository

        when:
        def ret = testSubject.findById(id)

        then:
        1 * repository.findOne(id) >> null
        thrown(ContentNotFoundException)

    }


}
