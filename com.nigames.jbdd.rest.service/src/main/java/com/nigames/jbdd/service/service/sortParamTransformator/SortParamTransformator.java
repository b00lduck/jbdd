package com.nigames.jbdd.service.service.sortParamTransformator;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 07.02.2015.
 */
public interface SortParamTransformator {

	boolean isResponsible(final String sortParam);

	String transform(String sortParam);

}
