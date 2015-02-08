package com.nigames.jbdd.service.service;

import com.nigames.jbdd.rest.dto.facet.IsDto;

import java.util.List;

/**
 * Created by developer on 2/8/15.
 */
public class DataList<DtoType extends IsDto>{

    private final List<DtoType> list;

    private final long count;

    public DataList(final List<DtoType> aList, final long aCount) {
        list = aList;
        count = aCount;
    }

    public List<DtoType> getList() {
        return list;
    }

    public long getCount() {
        return count;
    }
}
