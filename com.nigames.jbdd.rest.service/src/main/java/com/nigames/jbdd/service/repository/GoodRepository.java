package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.item.GoodEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface GoodRepository extends PagingAndSortingRepository<GoodEntity, Long> {

	List<GoodEntity> findByEnabled(boolean enabled);

}
