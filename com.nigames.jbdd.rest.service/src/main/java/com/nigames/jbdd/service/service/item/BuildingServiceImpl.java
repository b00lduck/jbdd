package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.rest.dto.Production;
import com.nigames.jbdd.service.conversion.dto.BuildingConversionService;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.repository.BuildingRepository;
import com.nigames.jbdd.service.service.AbstractRepositoryBackedService;
import com.nigames.jbdd.service.service.sortParamTransformator.NameSortParamTransformator;
import com.nigames.jbdd.service.service.subitem.ProductionService;
import com.nigames.jbdd.types.ResultList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * BuildingService implementation.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.item.BuildingServiceImpl
 */
@Service
public class BuildingServiceImpl extends AbstractRepositoryBackedService<BuildingEntity, Long, Building>
		implements BuildingService, InitializingBean {

	@Autowired
	private BuildingConversionService buildingConversionService;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private ProductionService productionService;

	@Autowired
	private GoodService goodService;

	@Override
	public void afterPropertiesSet() throws Exception {
		addSortParamTransformator(new NameSortParamTransformator());
	}

	@Override
	protected BuildingRepository getRepository() {
		return buildingRepository;
	}

	@Override
	protected ConversionServiceInterface<BuildingEntity, Building> getConversionService() {
		return buildingConversionService;
	}

	@Override
	public List<Building> findAllEnabled() {
		final List<BuildingEntity> entityList = buildingRepository.findByEnabled(true);
		return buildingConversionService.convertToDto(entityList);
	}

	@Override
	public ResultList<Good> getAddableProductionGoods(long buildingId) {

		final List<Good> goodList = goodService.findAllEnabled();

		final List<Good> ret = new ArrayList<>();

		final Set<Long> productionGoodList = getProductionsForBuilding(buildingId);

		for (final Good g : goodList) {

			// Cannot add already added productions
			if (productionGoodList.contains(g.getId())) {
				continue;
			}

			ret.add(g);

		}

		return ResultList.create(ret);
	}


	private Set<Long> getProductionsForBuilding(final long buildingId) {

		final Set<Long> ret = new HashSet<>();

		final ResultList<Production> reqList = productionService.findByBuildingId(buildingId);

		// TODO think about syntax vs. performance
		ret.addAll(reqList.stream().map(Production::getGoodId).collect(Collectors.toList()));

		return ret;
	}

}
