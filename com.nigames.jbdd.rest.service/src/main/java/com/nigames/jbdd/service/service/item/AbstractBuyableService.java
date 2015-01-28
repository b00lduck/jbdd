package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacet;
import com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacet;
import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Requirement;
import com.nigames.jbdd.rest.dto.facet.Buyable;
import com.nigames.jbdd.rest.dto.facet.IsDto;
import com.nigames.jbdd.service.conversion.dto.BuildingConversionService;
import com.nigames.jbdd.service.conversion.dto.GoodConversionService;
import com.nigames.jbdd.service.conversion.dto.TechnologyConversionService;
import com.nigames.jbdd.service.service.AbstractDtoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * AbstractBuyableService implementation.
 *
 * @param <DtoType>    Type of the DTO, must implement {@link Buyable}
 * @param <EntityType> Type of the Entity, must extend {@link com.nigames.jbdd.domain.entities.facet.BuyableEntityFacetImpl}
 * @author Daniel
 */
@SuppressWarnings("AbstractClassNeverImplemented")
@Service
abstract class AbstractBuyableService<DtoType extends Buyable & IsDto, EntityType extends BuyableEntityFacet> extends AbstractDtoService<DtoType, EntityType> {

	private static final Logger LOG = LoggerFactory.getLogger(AbstractBuyableService.class);

	@Autowired
	private transient GoodConversionService goodConversionService;

	@Autowired
	private transient TechnologyConversionService technologyConversionService;

	@Autowired
	private transient BuildingConversionService buildingConversionService;

	/**
	 * Returns all possible cost goods for this buyable. Only the cost goods which are not yet part
	 * of the costs and enabled will be returned.
	 *
	 * @param buyableId the ID of the buyable
	 * @return list of all possible cost goods of the buyable
	 */
	@Transactional
	public final List<Good> findAllAddableCostGoods(final Long buyableId) {

		final BuyableEntityFacetImpl buyableEntity =
				getEntityManager().find(BuyableEntityFacetImpl.class, buyableId);

		final String query = "SELECT o FROM Good o WHERE enabled=1";

		final List<GoodEntity> sourceList =
				getEntityManager().createQuery(query, GoodEntity.class).getResultList();

		final List<GoodEntity> resultList = sourceList.stream().filter(good -> !buyableEntity.hasCost(good)).collect(Collectors.toList());

		return goodConversionService.convertToDto(resultList);

	}

	/**
	 * Returns all possible requirement buyables for this buyable.<br>
	 * The Requirement buyable...
	 * <ul>
	 * <li>...must be enabled.</li>
	 * <li>...may not be part of the requirementList.</li>
	 * <li>...may not be the same as the buyable.</li>
	 * <li>...may not be part of any requirementList of all buyables which are required to buy this
	 * buyable.</li>
	 * </ul>
	 *
	 * @param buyableId the ID of the buyable
	 * @return list of all possible requirement buyables
	 */
	@Transactional
	public final Collection<IdentifyableEntityFacet> findAllAddableRequirementBuyables(final Long buyableId) {

		final Collection<IdentifyableEntityFacet> ret = new ArrayList<>();
		//final Collection<Buyable> allRequirementList = new ArrayList<>();

		//final List<TechnologyEntity> allTechnologies =
		//		getEntityManager().createNamedQuery(TechnologyEntity.NQ_FIND_ALL_ENABLED_TECHNOLOGIES,
		//				TechnologyEntity.class).getResultList();

		//final List<BuildingEntity> allBuildings =
		//		getEntityManager()
		//				.createNamedQuery(BuildingEntity.NQ_FIND_ALL_ENABLED_BUILDINGS, BuildingEntity.class)
		//				.getResultList();
/*
		Collection<Buyable> x = technologyConversionService.convertToDto(allTechnologies);
		allRequirementList.addAll(x);

		allRequirementList.addAll(allTechnologies.stream().map(technologyConversionService::convertToDto).collect(Collectors.toList()));
		allRequirementList.addAll(allBuildings.stream().map(buildingConversionService::convertToDto).collect(Collectors.toList()));

		// The entity will be checked against all available buyables in the
		// database
		ret.addAll(allRequirementList.stream().filter(entity -> !hasRequirement(entity.getId(), buyableId)).collect(Collectors.toList()));
*/
		return ret;
	}

	// @Transactional
	// public final void addCost(final Long buyableId, final Cost cost) {
	// costSubService.create(buyableId, cost);
	// }
	//
	// @Transactional
	// public final void deleteCost(final Long costId) {
	// costSubService.remove(costId);
	// }
	//
	// @Transactional
	// public final void updateCost(final Long costId, final Cost cost) {
	// costSubService.update(costId, cost);
	// }
	//
	// @Transactional
	// public List<Cost> getAllCosts(final Long buyableId) {
	// return costSubService.getAll(buyableId);
	// }


	public final void addRequirement(final Requirement requirement) {
		// attachRequirementBuyable(requirement);
		// requirementSubService.create(requirement);
		// requirement.getBuyable().getRequirementList().add(requirement);
		LOG.error("NOT IMPLEMENTED"); //NON-NLS
		// TODO: implement
	}

	public final void deleteRequirement(final Requirement requirement) {
		// attachRequirementBuyable(requirement);
		// requirementSubService.remove(requirement);
		// requirement.getBuyable().getRequirementList().remove(requirement);
		LOG.error("NOT IMPLEMENTED"); //NON-NLS
		// TODO: implement
	}

	public final void updateRequirement(final Requirement requirement) {
		// requirementSubService.update(requirement);
		LOG.error("NOT IMPLEMENTED"); //NON-NLS
		// TODO: implement
	}


	@SuppressWarnings({"ReturnOfNull", "SameReturnValue"})
	public List<Requirement> getAllRequirements(final Buyable buyable) {
		// return requirementSubService.getAll(buyable);
		LOG.error("NOT IMPLEMENTED"); //NON-NLS
		// TODO: Implement
		return null;
	}


	/**
	 * Returns true if the object has the subject as requirement. Recursive!
	 *
	 * @param objectBuyableId  the buyable ID
	 * @param subjectBuyableId the requirement ID
	 * @return true if the object has the subject as requirement
	 */
	private boolean hasRequirement(final Long objectBuyableId, final Long subjectBuyableId) {

		// // Check if object and subject is the same buyable
		// if (subject.getId().equals(object.getId())) {
		// return true;
		// }
		//
		// // check if the object is required by the subject
		// for (Requirement req : subject.getRequirementList()) {
		// if (req.getRequiredBuyable().getId().equals(object.getId())) {
		// return true;
		// }
		// }
		//
		// //
		// for (Requirement req : object.getRequirementList()) {
		// if (hasRequirement(req.getRequiredBuyable(), subject)) {
		// return true;
		// }
		// }
		LOG.error("NOT IMPLEMENTED"); //NON-NLS
		// TODO: implement
		return false;
	}

}
