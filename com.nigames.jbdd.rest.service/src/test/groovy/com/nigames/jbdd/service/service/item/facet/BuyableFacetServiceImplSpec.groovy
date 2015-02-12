package com.nigames.jbdd.service.service.item.facet

import com.nigames.jbdd.rest.dto.Cost
import com.nigames.jbdd.rest.dto.Good
import com.nigames.jbdd.service.service.item.GoodService
import com.nigames.jbdd.service.service.subitem.buyable.CostService
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

    def "getAddableCostGoods should "() {

        given:
        def goodService = Mock(GoodService)
        def costService = Mock(CostService)
        def service = new BuyableFacetServiceImpl(
                goodService: goodService,
                costService: costService)
        def buyableId = 5

        def goodList = new ArrayList([Mock(Good), Mock(Good), Mock(Good)])
        def costList = new ArrayList([Mock(Cost)])
        def resultList = new ResultList<Cost>(costList, 1)

        when:
        def result = service.getAddableCostGoods(buyableId)

        then:
        1 * costList[0].getGoodId() >> 2

        1 * goodList[0].getId() >> 1
        1 * goodList[1].getId() >> 2
        1 * goodList[2].getId() >> 3

        1 * goodService.findAllEnabled() >> goodList
        1 * costService.findByBuyableId(buyableId) >> resultList

        result.size() == 2
        result.get(0) == goodList[0]
        result.get(1) == goodList[2]
        result.get(0) != goodList[2]
        result.get(1) != goodList[0]

    }

}

