package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import com.nigames.jbdd.rest.dto.Building;
import com.nigames.jbdd.service.conversion.dto.BuildingConversionService;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.service.AbstractDtoService;
import com.nigames.jbdd.service.service.querystrategy.BuildingQueryStrategy;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * BuildingService implementation.
 *
 * @author Daniel
 * @see com.nigames.jbdd.service.service.item.BuildingServiceImpl
 */
@Service
public class BuildingServiceImpl extends AbstractDtoService<Building, BuildingEntity> implements BuildingService {

    @Autowired
    private transient BuildingConversionService buildingConversionService;

    @Autowired
    private transient BuildingQueryStrategy buildingQueryStrategy;

    @Override
    @Transactional
    public Building create(final Building dto) {
        return super.create(dto);
    }

    @Override
    @Transactional
    public Building update(final long id, final Building dto) {
        return super.update(id, dto);
    }

    @Override
    @Transactional
    public void delete(final long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    public List<Building> findAll(final LimitParams limitParams, final SortParams sortParams) {
        return super.findAll(limitParams, sortParams);
    }

    @Override
    @Transactional
    public List<Building> findAll(final LimitParams limitParams, final SortParams sortParams,
                              final QueryStrategy<BuildingEntity> queryStrategy, final Object... queryParams) {
        return super.findAll(limitParams, sortParams, queryStrategy, queryParams);
    }

    @Override
    @Transactional
    public Building findById(final long entityId) {
        return super.findById(entityId);
    }

    @Override
    @Transactional
    public long getCount() {
        return super.getCount();
    }

    @Override
    protected Class<BuildingEntity> getEntityClass() {
        return BuildingEntity.class;
    }

    @Override
    protected ConversionServiceInterface<BuildingEntity, Building> getConversionService() {
        return buildingConversionService;
    }

    @Override
    protected QueryStrategy<BuildingEntity> getDefaultQueryStrategy() {
        return buildingQueryStrategy;
    }

}
