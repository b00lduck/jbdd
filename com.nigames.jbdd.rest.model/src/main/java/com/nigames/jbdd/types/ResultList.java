package com.nigames.jbdd.types;

import java.util.*;
import java.util.function.UnaryOperator;

/**
 * Created by developer on 2/8/15.
 */
public class ResultList<DtoType> implements List<DtoType> {

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

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<DtoType> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    @Override
    public boolean add(DtoType dtoType) {
        return list.add(dtoType);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends DtoType> c) {
        return list.addAll(c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends DtoType> c) {
        return list.addAll(index, c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return list.retainAll(c);
    }

    @Override
    public void replaceAll(UnaryOperator<DtoType> operator) {
        list.replaceAll(operator);
    }

    @Override
    public void sort(Comparator<? super DtoType> c) {
        list.sort(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean equals(Object o) {
        return list.equals(o);
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public DtoType get(int index) {
        return list.get(index);
    }

    @Override
    public DtoType set(int index, DtoType element) {
        return list.set(index, element);
    }

    @Override
    public void add(int index, DtoType element) {
        list.add(index, element);
    }

    @Override
    public DtoType remove(int index) {
        return list.remove(index);
    }

    @Override
    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    @Override
    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    @Override
    public ListIterator<DtoType> listIterator() {
        return list.listIterator();
    }

    @Override
    public ListIterator<DtoType> listIterator(int index) {
        return list.listIterator(index);
    }

    @Override
    public List<DtoType> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

    @Override
    public Spliterator<DtoType> spliterator() {
        return list.spliterator();
    }

    public long getTotalCount() {
        return count;
    }
}
