package com.nigames.jbdd.service.service.item;

import com.nigames.jbdd.domain.entities.item.StoragetypeEntity;
import com.nigames.jbdd.rest.dto.Storagetype;
import com.nigames.jbdd.service.conversion.dto.ConversionServiceInterface;
import com.nigames.jbdd.service.conversion.dto.StoragetypeConversionService;
import com.nigames.jbdd.service.service.AbstractDtoService;
import com.nigames.jbdd.service.service.querystrategy.LimitParams;
import com.nigames.jbdd.service.service.querystrategy.QueryStrategy;
import com.nigames.jbdd.service.service.querystrategy.SortParams;
import com.nigames.jbdd.service.service.querystrategy.StoragetypeQueryStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * StoragetypeService implementation.
 *
 * @author Daniel
 * @see StoragetypeServiceImpl
 */
@Service
public class StoragetypeServiceImpl extends AbstractDtoService<Storagetype, StoragetypeEntity>
        implements StoragetypeService {

    @Autowired
    private transient StoragetypeConversionService storagetypeConversionService;

    @Autowired
    private transient StoragetypeQueryStrategy storagetypeQueryStrategy;

    @Override
    @Transactional
    public Storagetype create(final Storagetype dto) {
        return super.create(dto);
    }

    @Override
    @Transactional
    public Storagetype update(final long id, final Storagetype dto) {
        return super.update(id, dto);
    }

    @Override
    @Transactional
    public void delete(final long id) {
        super.delete(id);
    }

    @Override
    @Transactional
    public List<Storagetype> findAll(final LimitParams limitParams, final SortParams sortParams) {
        return super.findAll(limitParams, sortParams);
    }

    @Override
    @Transactional
    public List<Storagetype> findAll(final LimitParams limitParams, final SortParams sortParams,
                                     final QueryStrategy<StoragetypeEntity> queryStrategy,
                                     final Object... queryParams) {
        return super.findAll(limitParams, sortParams, queryStrategy, queryParams);
    }

    @Override
    @Transactional
    public Storagetype findById(final long entityId) {
        return super.findById(entityId);
    }

    @Override
    @Transactional
    public long getCount() {
        return super.getCount();
    }

    @Override
    protected Class<StoragetypeEntity> getEntityClass() {
        return StoragetypeEntity.class;
    }

    @Override
    protected ConversionServiceInterface<StoragetypeEntity, Storagetype> getConversionService() {
        return storagetypeConversionService;
    }

    @Override
    protected QueryStrategy<StoragetypeEntity> getDefaultQueryStrategy() {
        return storagetypeQueryStrategy;
    }

}
