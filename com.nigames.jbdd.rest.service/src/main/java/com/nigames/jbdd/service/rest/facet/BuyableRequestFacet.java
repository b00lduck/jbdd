package com.nigames.jbdd.service.rest.facet;

import com.nigames.jbdd.rest.api.aspect.BuyableRequestInterface;
import com.nigames.jbdd.rest.dto.Cost;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.service.item.GoodService;
import com.nigames.jbdd.service.service.item.facet.BuyableFacetService;
import com.nigames.jbdd.service.service.subitem.buyable.CostService;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyableRequestFacet implements BuyableRequestInterface {

    @Autowired
    private CostService costService;

    @Autowired
    private GoodService goodService;

	@Autowired
	private BuyableFacetService buyableFacetService;

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

	    final long total = buyableFacetService.getAddableCostGoodsCount(itemId);
	    final List<Good> data = buyableFacetService.getAddableCostGoods(itemId, limitParams, sortParams);

        return new DtoList<>(data, total, limitParams);
    }

	public Cost createCost(final long buyableId, final Cost dto) {
		checkConsistency(buyableId, dto);
		return costService.create(dto);
	}

	public void deleteCost(final long buyableId, final long goodId) {
		costService.delete(buyableId, goodId);
	}

	public Cost updateCost(final long buyableId, final Cost dto) {
		checkConsistency(buyableId, dto);
		return costService.update(dto);
	}

	private void checkConsistency(final long buyableId, final Cost dto) {
		if (buyableId != dto.getBuyableId()) {
			throw new IllegalArgumentException("buyableId must match the buyableId in DTO");
		}
	}

}
