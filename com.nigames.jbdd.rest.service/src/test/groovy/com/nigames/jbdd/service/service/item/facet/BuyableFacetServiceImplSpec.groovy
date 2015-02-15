package com.nigames.jbdd.service.service.item.facet

import com.nigames.jbdd.rest.dto.*
import com.nigames.jbdd.service.service.item.BuildingService
import com.nigames.jbdd.service.service.item.GoodService
import com.nigames.jbdd.service.service.item.TechnologyService
import com.nigames.jbdd.service.service.subitem.buyable.CostService
import com.nigames.jbdd.service.service.subitem.buyable.CyclicRequirementException
import com.nigames.jbdd.service.service.subitem.buyable.RequirementService
import com.nigames.jbdd.types.ResultList
import spock.lang.Specification

/**
 * This file is part of JBdD by nigames.de
 *
 * Created by Daniel on 12.02.2015.
 */
class BuyableFacetServiceImplSpec extends Specification {

    def "getAddableCostGoods should return empty list on no data"() {

        given:
        def goodService = Mock(GoodService)
        def costService = Mock(CostService)
        def service = new BuyableFacetServiceImpl(
                goodService: goodService,
                costService: costService)
        def buyableId = 5

        def goodList = new ArrayList<Good>()
        def list = new ArrayList<Cost>()
        def resultList = new ResultList<Cost>(list, 0)

        when:
        def result = service.getAddableCostGoods(buyableId)

        then:
        1 * goodService.findAllEnabled() >> goodList
        1 * costService.findByBuyableId(buyableId) >> resultList
        result.empty
    }

    def "getAddableCostGoods should return all goods not already added as cost"() {

        given:
        def goodService = Mock(GoodService)
        def costService = Mock(CostService)
        def service = new BuyableFacetServiceImpl(
                goodService: goodService,
                costService: costService)
        def buyableId = 5

        def good1 = new Good(id: 1)
        def good2 = new Good(id: 2)
        def good3 = new Good(id: 3)
        def cost1 = new Cost(goodId: 2)

        def goodList = new ArrayList([good1, good2, good3])
        def costList = new ArrayList([cost1])
        def resultList = new ResultList<Cost>(costList, 1)

        when:
        def result = service.getAddableCostGoods(buyableId)

        then:
        1 * goodService.findAllEnabled() >> goodList
        1 * costService.findByBuyableId(buyableId) >> resultList

        result.size() == 2
        result.get(0) == goodList[0]
        result.get(1) == goodList[2]
        result.get(0) != goodList[2]
        result.get(1) != goodList[0]

    }


    def "getAddableRequirementBuyables should return all items except itself"() {

        // Item     Name            requirements
        // 1        Builiding 1     no requirements
        // 2        Builiding 2     no requirements
        // 3        Technology 1    no requirements
        // 4        Technology 2    no requirements
        // should return all items except itself (item 2,3,4)

        given:
        def requirementService = Mock(RequirementService)
        def buildingService = Mock(BuildingService)
        def technologyService = Mock(TechnologyService)
        def service = new BuyableFacetServiceImpl(
                requirementService: requirementService,
                technologyService: technologyService,
                buildingService: buildingService)
        def buyableId = 1

        // Setup buildings
        def buildingList = new ArrayList<Building>()
        buildingList.addAll([new Building(id: 1), new Building(id: 2)])

        // Setup technologies
        def technologyList = new ArrayList<Technology>()
        technologyList.addAll([new Technology(id: 3), new Technology(id: 4)])

        // Setup requirement lists
        def item1req = new ArrayList<Requirement>()
        def item2req = new ArrayList<Requirement>()
        def item3req = new ArrayList<Requirement>()
        def item4req = new ArrayList<Requirement>()

        1 * buildingService.findAllEnabled() >> buildingList
        1 * technologyService.findAllEnabled() >> technologyList

        requirementService.findByBuyableId(1) >> ResultList.create(item1req)
        requirementService.findByBuyableId(2) >> ResultList.create(item2req)
        requirementService.findByBuyableId(3) >> ResultList.create(item3req)
        requirementService.findByBuyableId(4) >> ResultList.create(item4req)

        when:
        def result = service.getAddableRequirementBuyables(buyableId)

        then:
        result.size() == 3
        result.contains(buildingList[1])
        result.contains(technologyList[0])
        result.contains(technologyList[1])

    }


    def "getAddableRequirementBuyables should return Technology 2 only"() {

        // Item     Name            requirements
        // 1        Builiding 1     no requirements
        // 2        Builiding 2     Building 1
        // 3        Technology 1    Building 2
        // 4        Technology 2    no requirements
        // should return all items except itself (item 2,3,4)

        given:
        def requirementService = Mock(RequirementService)
        def buildingService = Mock(BuildingService)
        def technologyService = Mock(TechnologyService)
        def service = new BuyableFacetServiceImpl(
                requirementService: requirementService,
                technologyService: technologyService,
                buildingService: buildingService)
        def buyableId = 1

        // Setup buildings
        def buildingList = new ArrayList<Building>()
        buildingList.addAll([new Building(id: 1), new Building(id: 2)])

        // Setup technologies
        def technologyList = new ArrayList<Technology>()
        technologyList.addAll([new Technology(id: 3), new Technology(id: 4)])

        // Setup requirement lists
        def item1req = new ArrayList<Requirement>()
        def item2req = new ArrayList<Requirement>()
        item2req.add(new Requirement(buyableId: 2, requiredBuyableId: 1))
        def item3req = new ArrayList<Requirement>()
        item3req.add(new Requirement(buyableId: 3, requiredBuyableId: 2))
        def item4req = new ArrayList<Requirement>()


        1 * buildingService.findAllEnabled() >> buildingList
        1 * technologyService.findAllEnabled() >> technologyList

        requirementService.findByBuyableId(1) >> ResultList.create(item1req)
        requirementService.findByBuyableId(2) >> ResultList.create(item2req)
        requirementService.findByBuyableId(3) >> ResultList.create(item3req)
        requirementService.findByBuyableId(4) >> ResultList.create(item4req)

        when:
        def result = service.getAddableRequirementBuyables(buyableId)

        then:
        result.size() == 1
        result.contains(technologyList[1])

    }

    def "getAddableRequirementBuyables should throw execption because of cyclic requirement"() {

        // Item     Name            requirements
        // 1        Builiding 1     Technology 2
        // 2        Builiding 2     Building 1
        // 3        Technology 1    Building 2
        // 4        Technology 2    Technology 1
        // should detect endless loop

        given:
        def requirementService = Mock(RequirementService)
        def buildingService = Mock(BuildingService)
        def technologyService = Mock(TechnologyService)
        def service = new BuyableFacetServiceImpl(
                requirementService: requirementService,
                technologyService: technologyService,
                buildingService: buildingService)
        def buyableId = 1

        // Setup buildings
        def buildingList = new ArrayList<Building>()
        buildingList.addAll([new Building(id: 1), new Building(id: 2)])

        // Setup technologies
        def technologyList = new ArrayList<Technology>()
        technologyList.addAll([new Technology(id: 3), new Technology(id: 4)])

        // Setup requirement lists
        def item1req = new ArrayList<Requirement>()
        item1req.add(new Requirement(buyableId: 1, requiredBuyableId: 4))
        def item2req = new ArrayList<Requirement>()
        item2req.add(new Requirement(buyableId: 2, requiredBuyableId: 1))
        def item3req = new ArrayList<Requirement>()
        item3req.add(new Requirement(buyableId: 3, requiredBuyableId: 2))
        def item4req = new ArrayList<Requirement>()
        item4req.add(new Requirement(buyableId: 4, requiredBuyableId: 3))

        1 * buildingService.findAllEnabled() >> buildingList
        1 * technologyService.findAllEnabled() >> technologyList

        requirementService.findByBuyableId(1) >> ResultList.create(item1req)
        requirementService.findByBuyableId(2) >> ResultList.create(item2req)
        requirementService.findByBuyableId(3) >> ResultList.create(item3req)
        requirementService.findByBuyableId(4) >> ResultList.create(item4req)

        when:
        def result = service.getAddableRequirementBuyables(buyableId)

        then:
        thrown(CyclicRequirementException)

    }


    def "getAddableRequirementBuyables ahould not return already added requirement buyables"() {

        // Item     Name            requirements
        // 1        Builiding 1     Building 2
        // 2        Builiding 2     no requirement
        // 3        Technology 1    no requirement
        // 4        Technology 2    no requirement
        // should detect endless loop

        given:
        def requirementService = Mock(RequirementService)
        def buildingService = Mock(BuildingService)
        def technologyService = Mock(TechnologyService)
        def service = new BuyableFacetServiceImpl(
                requirementService: requirementService,
                technologyService: technologyService,
                buildingService: buildingService)
        def buyableId = 1

        // Setup buildings
        def buildingList = new ArrayList<Building>()
        buildingList.addAll([new Building(id: 1), new Building(id: 2)])

        // Setup technologies
        def technologyList = new ArrayList<Technology>()
        technologyList.addAll([new Technology(id: 3), new Technology(id: 4)])

        // Setup requirement lists
        def item1req = new ArrayList<Requirement>()
        item1req.add(new Requirement(buyableId: 1, requiredBuyableId: 2))
        def item2req = new ArrayList<Requirement>()
        def item3req = new ArrayList<Requirement>()
        def item4req = new ArrayList<Requirement>()

        1 * buildingService.findAllEnabled() >> buildingList
        1 * technologyService.findAllEnabled() >> technologyList

        requirementService.findByBuyableId(1) >> ResultList.create(item1req)
        requirementService.findByBuyableId(2) >> ResultList.create(item2req)
        requirementService.findByBuyableId(3) >> ResultList.create(item3req)
        requirementService.findByBuyableId(4) >> ResultList.create(item4req)

        when:
        def result = service.getAddableRequirementBuyables(buyableId)

        then:
        result.size() == 2
        result.contains(technologyList[0])
        result.contains(technologyList[1])

    }

}

