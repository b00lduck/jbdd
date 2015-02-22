package com.nigames.jbdd.service.service.subitem;

import com.nigames.jbdd.types.LimitParams;
import com.nigames.jbdd.types.SortParams;
import org.springframework.data.domain.Pageable;

import javax.annotation.Nonnull;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 22.02.2015.
 */
public class TestProductionServiceImpl extends ProductionServiceImpl {

	public Pageable mockPageable;

	@Override
	@Nonnull
	protected Pageable createPageable(@Nonnull LimitParams l, @Nonnull SortParams s) {
		return mockPageable;
	}

}
