package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.domain.entities.subitem.ProductionEntity;
import com.nigames.jbdd.domain.entities.subitem.ProductionEntityPK;
import com.nigames.jbdd.rest.dto.Production;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ProductionConversionService extends AbstractConversionService<ProductionEntity, Production> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private GoodConversionService goodConversionService;

	@Override
	public ProductionEntity getNewEntityInstance() {
		return new ProductionEntity();
	}

	@Override
	public Production getNewDtoInstance(final Class<?> entityClass) {
		return new Production();
	}


	@Override
	protected void addModules() {
	}

	@Override
	protected void updateDtoFromEntity(final Production dto, final ProductionEntity entity) {
		dto.setBuildingId(entity.getId().getBuildingId());
		dto.setGood(goodConversionService.convertToDto(entity.getGood()));
		dto.setGoodId(entity.getId().getGoodId());
		dto.setAmount(entity.getAmount());
	}

	@Override
	public void updateEntityFromDto(final Production dto, final ProductionEntity entity) {
		entity.setAmount(dto.getAmount());

		// On the contrary to the other converters, in this particulary case the ID has to be setLang.
		// (composite key which is not auto generated)
		final ProductionEntityPK productionEntityPK = new ProductionEntityPK(dto.getBuildingId(), dto.getGoodId());
		entity.setId(productionEntityPK);

		final BuildingEntity building = entityManager.find(BuildingEntity.class,
				dto.getBuildingId());
		entity.setBuilding(building);

		final GoodEntity good = entityManager.find(GoodEntity.class, dto.getGoodId());
		entity.setGood(good);
	}

}
