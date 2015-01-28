package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.GoodConversionService;
import com.nigames.jbdd.service.service.AbstractDtoService;
import com.nigames.jbdd.service.service.querystrategy.GoodQueryStrategy;
import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.types.SortParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * GoodService implementation.
 *
 * @author Daniel
 * @see GoodServiceImpl
 */
@Service
public class GoodServiceImpl extends AbstractDtoService<Good, GoodEntity> implements GoodService {

    @Autowired
    private transient GoodConversionService goodConversionService;

    @Autowired
    private transient GoodQueryStrategy goodQueryStrategy;

    @Override
    @Transactional
    public Good create(final Good dto) {
        return super.create(dto);
    }

    @Override
    @Transactional
    public Good update(final long id, final Good dto) {
        return super.update(id, dto);
    }

    @Override
    @Transactional
    public void delete(final long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    public List<Good> findAll(final LimitParams limitParams, final SortParams sortParams) {
        return super.findAll(limitParams, sortParams);
    }

    @Override
    @Transactional
    public List<Good> findAll(final LimitParams limitParams, final SortParams sortParams,
                              final QueryStrategy<GoodEntity> queryStrategy, final Object... queryParams) {
        return super.findAll(limitParams, sortParams, queryStrategy, queryParams);
    }

    @Override
    @Transactional
    public Good findById(final long entityId) {
        return super.findById(entityId);
    }

    @Override
    @Transactional
    public long getCount() {
        return super.getCount();
    }

    @Override
    protected Class<GoodEntity> getEntityClass() {
        return GoodEntity.class;
    }

    @Override
    protected ConversionServiceInterface<GoodEntity, Good> getConversionService() {
        return goodConversionService;
    }

    @Override
    protected QueryStrategy<GoodEntity> getDefaultQueryStrategy() {
        return goodQueryStrategy;
    }

}
