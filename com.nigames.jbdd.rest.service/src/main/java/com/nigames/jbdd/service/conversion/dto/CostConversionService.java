package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntityPK;
import com.nigames.jbdd.rest.dto.Cost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CostConversionService extends AbstractConversionService<CostEntity, Cost> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private GoodConversionService goodConversionService;

	@Override
	public CostEntity getNewEntityInstance() {
		return new CostEntity();
	}

	@Override
	public Cost getNewDtoInstance() {
		return new Cost();
	}


	@Override
	protected void addModules() {
	}

	@Override
	protected void updateDtoFromEntity(final Cost dto, final CostEntity entity) {
		dto.setBuyableId(entity.getId().getBuyableId());
		dto.setGood(goodConversionService.convertToDto(entity.getGood()));
		dto.setGoodId(entity.getId().getGoodId());
		dto.setAmount(entity.getAmount());
	}

	@Override
	public void updateEntityFromDto(final Cost dto, final CostEntity entity) {
		entity.setAmount(dto.getAmount());

		// On the contrary to the other converters, in this particulary case the ID has to be set.
		// (composite key which is not auto generated)
		final CostEntityPK costEntityPK = new CostEntityPK(dto.getBuyableId(), dto.getGoodId());
		entity.setId(costEntityPK);

		final BuyableEntityFacetImpl buyableFacet = entityManager.find(BuyableEntityFacetImpl.class,
				dto.getBuyableId());
		entity.setBuyableFacet(buyableFacet);

		final GoodEntity good = entityManager.find(GoodEntity.class, dto.getGoodId());
		entity.setGood(good);
	}

}
