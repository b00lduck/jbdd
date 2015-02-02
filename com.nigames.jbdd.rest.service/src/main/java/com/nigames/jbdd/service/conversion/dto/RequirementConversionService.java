package com.nigames.jbdd.service.conversion.dto;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.item.AbstractItemEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntityPK;
import com.nigames.jbdd.rest.dto.Requirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class RequirementConversionService extends AbstractConversionService<RequirementEntity, Requirement> {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private BuyableConversionService buyableConversionService;

	@Override
	public RequirementEntity getNewEntityInstance() {
		return new RequirementEntity();
	}

	@Override
	public Requirement getNewDtoInstance(final Class<?> entityClass) {
		return new Requirement();
	}


	@Override
	protected void addModules() {
	}

	@Override
	protected void updateDtoFromEntity(final Requirement dto, final RequirementEntity entity) {
		dto.setBuyableId(entity.getId().getBuyableId());
		final AbstractItemEntity requiredBuyableEntity = entity.getRequiredBuyableFacet().getItem();
		dto.setRequiredBuyable(buyableConversionService.convertToDto(requiredBuyableEntity));
		dto.setRequiredBuyableId(requiredBuyableEntity.getId());
		dto.setAmount(entity.getAmount());
	}

	@Override
	public void updateEntityFromDto(final Requirement dto, final RequirementEntity entity) {
		entity.setAmount(dto.getAmount());

		// On the contrary to the other converters, in this particulary case the ID has to be setLang.
		// (composite key which is not auto generated)
		final RequirementEntityPK requirementEntityPK = new RequirementEntityPK(dto.getBuyableId(),
				dto.getRequiredBuyableId());
		entity.setId(requirementEntityPK);

		final BuyableEntityFacetImpl buyableFacet = entityManager.find(BuyableEntityFacetImpl.class,
				dto.getBuyableId());
		entity.setBuyableFacet(buyableFacet);

		final BuyableEntityFacetImpl requiredBuyableFacet = entityManager.find(BuyableEntityFacetImpl.class,
				dto.getRequiredBuyableId());
		entity.setRequiredBuyableFacet(requiredBuyableFacet);

	}

}
