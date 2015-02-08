package com.nigames.jbdd.types;

import java.util.List;

/**
 * Created by developer on 2/8/15.
 */
public class ResultList<DtoType> {

    private final List<DtoType> list;

    private final long count;

    public static <DtoType> ResultList<DtoType> create(final List<DtoType> list) {
        return create(list, list.size());
    }

    public static <DtoType> ResultList<DtoType> create(final List<DtoType> list, final long total) {
        return new ResultList<>(list, total);
    }

    private ResultList(final List<DtoType> list, final long aCount) {
        this.list = list;
        count = aCount;
    }

    public List<DtoType> getList() {
        return list;
    }

    public long getCount() {
        return count;
    }
}
