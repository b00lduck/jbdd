package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;
import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.domain.entities.item.TechnologyEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Technology;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuyableConversionService extends AbstractConversionService<AbstractItemEntity, Buyable> {

	@Autowired
	private BuildingConversionService buildingConversionService;

	@Autowired
	private TechnologyConversionService technologyConversionService;

	@Override
	public AbstractItemEntity getNewEntityInstance() {
		throw new IllegalArgumentException("instatiation of abstract item is not possible.");
	}

	@Override
	public Buyable getNewDtoInstance(final Class entityClass) {
		if (entityClass.equals(BuildingEntity.class)) {
			return new Building();
		}
		if (entityClass.equals(TechnologyEntity.class)) {
			return new Technology();
		}

		throw new IllegalArgumentException("unknown entityClass " + entityClass.getSimpleName());
	}


	@Override
	protected void addModules() {
	}

	@Override
	protected void updateDtoFromEntity(final Buyable dto, final AbstractItemEntity entity) {

		if (entity instanceof BuildingEntity) {
			buildingConversionService.updateDto((BuildingEntity) entity, (Building) dto);
		} else if (entity instanceof TechnologyEntity) {
			technologyConversionService.updateDto((TechnologyEntity) entity, (Technology) dto);
		} else {
			throw new IllegalArgumentException("invalid DTO or EntityType was provided. DTO:" +
					dto.getClass().getSimpleName() + " Entity:" + entity.getClass().getSimpleName());
		}

	}

	@Override
	public void updateEntityFromDto(final Buyable dto, final AbstractItemEntity entity) {
		if ((entity instanceof BuildingEntity) && (dto instanceof Building)) {
			buildingConversionService.updateEntityFromDto((Building) dto, (BuildingEntity) entity);
		} else if ((entity instanceof TechnologyEntity) && (dto instanceof Technology)) {
			technologyConversionService.updateEntityFromDto((Technology) dto, (TechnologyEntity) entity);
		} else {
			throw new IllegalArgumentException("invalid DTO or EntityType was provided. DTO:" +
					dto.getClass().getSimpleName() + " Entity:" + entity.getClass().getSimpleName());
		}
	}

}
