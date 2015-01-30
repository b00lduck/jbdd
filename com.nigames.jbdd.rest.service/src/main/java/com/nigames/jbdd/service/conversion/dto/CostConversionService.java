package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;
import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntityPK;
import com.nigames.jbdd.rest.dto.Cost;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class CostConversionService extends AbstractConversionService<CostEntity, Cost> {

	@PersistenceContext
	private EntityManager entityManager;

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
		dto.setGoodId(entity.getId().getGoodId());
		dto.setAmount(entity.getAmount());
	}

	@Override
	public void updateEntityFromDto(final Cost dto, final CostEntity entity) {
		entity.setAmount(dto.getAmount());

		// In this case the ID has to be set (composite key which is not auto generated)
		entity.setId(new CostEntityPK(dto.getBuyableId(), dto.getGoodId()));

		//AbstractItemEntity itemEntity = entityManager.find(AbstractItemEntity.class, dto.getBuyableId());
		/*
		if (itemEntity instanceof BuyableEntityFacet) {
			entity.setBuyable(itemEntity);
		} else {
			throw new IllegalArgumentException("given DTO is not a buyable");
		}
		*/

		//GoodEntity goodEntity = entityManager.find(GoodEntity.class, dto.getGoodId());
		//entity.setGood(goodEntity);
	}

}
