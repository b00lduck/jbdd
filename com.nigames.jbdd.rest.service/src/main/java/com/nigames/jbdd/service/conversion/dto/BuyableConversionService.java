package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;
import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyableConversionService extends AbstractConversionService<AbstractItemEntity, Buyable> {

	@Autowired
	private BuildingConversionService buildingConversionService;

	@Override
	public AbstractItemEntity getNewEntityInstance() {
		throw new IllegalArgumentException("instatiation of abstract item is not possible.");
	}

	@Override
	public Buyable getNewDtoInstance() {
		return null;
	}

	@Override
	protected void addModules() {
	}

	@Override
	protected void updateDtoFromEntity(Buyable dto, final AbstractItemEntity entity) {

		if (entity instanceof BuildingEntity) {
			dto = new Building();
			buildingConversionService.updateDtoFromEntity((Building) dto, (BuildingEntity) entity);
		} else {
			throw new IllegalArgumentException("invalid DTO or EntityType was provided. DTO:" +
					dto.getClass().getSimpleName() + " Entity:" + entity.getClass().getSimpleName());
		}
	}

	@Override
	public void updateEntityFromDto(final Buyable dto, final AbstractItemEntity entity) {
		if (entity instanceof BuildingEntity && dto instanceof Building) {
			buildingConversionService.updateEntityFromDto((Building) dto, (BuildingEntity) entity);
		} else {
			throw new IllegalArgumentException("invalid DTO or EntityType was provided. DTO:" +
					dto.getClass().getSimpleName() + " Entity:" + entity.getClass().getSimpleName());
		}
	}

}
