package com.nigames.jbdd.service.rest;

import com.nigames.jbdd.rest.api.GoodRequestInterface;
import com.nigames.jbdd.rest.dto.DtoList;
import com.nigames.jbdd.rest.dto.Good;
import com.nigames.jbdd.service.service.item.GoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@SuppressWarnings("ALL")
@Component
public class GoodRequest extends AbstractRequest<Good> implements GoodRequestInterface {

    @Autowired
    private transient GoodService goodService;

    @Override
    protected GoodService getService() {
        return goodService;
    }

    @Override
    public DtoList<Good> getAll(final Long first, final Long size, final String sort, final Boolean desc) {
        return super.getAll(first, size, sort, desc);
    }

    @Override
    public Good getById(final long id) {
        return getService().findById(id);
    }

    @Override
    public Good update(final long id, final Good dto) {
        return getService().update(id, dto);
    }

    @Override
    public Good create(final Good dto) {
        return getService().create(dto);
    }

}
