package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.rest.dto.Cost;
import org.springframework.stereotype.Service;

@Service
public class CostConversionService extends AbstractConversionService<CostEntity, Cost> {

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
	}

}
