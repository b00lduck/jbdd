package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.item.BuildingEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface BuildingRepository extends PagingAndSortingRepository<BuildingEntity, Long> {

}
