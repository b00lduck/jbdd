package com.nigames.jbdd.service.rest.facet;

import com.nigames.jbdd.rest.api.aspect.BuyableRequestInterface;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import com.nigames.jbdd.service.service.subitem.buyable.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyableRequestFacet implements BuyableRequestInterface {

    @Autowired
    private CostService costService;

    @Autowired
    private GoodService goodService;


    public DtoList<Cost> getCosts(final long itemId,
                                  final Long first, final Long size,
                                  final String sort, final Boolean desc) {

        final LimitParams limitParams = LimitParams.create(first, size);
        final SortParams sortParams = SortParams.create(sort, desc);

        final List<Cost> data = costService.findByBuyableId(itemId, limitParams, sortParams);
        final long total = costService.getCount();

        return new DtoList<>(data, total, limitParams);
    }

    public DtoList<Good> getAddableCosts(final long itemId,
                                         final Long first, final Long size,
                                         final String sort, final Boolean desc) {

        final LimitParams limitParams = LimitParams.create(first, size);
        final SortParams sortParams = SortParams.create(sort, desc);
        final long total = goodService.getCount();
        final List<Good> data = goodService.findAll(limitParams, sortParams);

        return new DtoList<>(data, total, limitParams);
    }

    public Cost createCost(final long itemId, final Cost dto) {
        return null;
    }

    public Cost deleteCost(final long itemId, final long goodId) {
        return null;
    }

    public Cost updateCost(final long itemId, final Cost dto) {
        return null;
    }

}
