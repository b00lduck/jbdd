package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface RequirementRepository extends PagingAndSortingRepository<RequirementEntity, RequirementEntityPK> {

	long countByIdBuyableId(@Param("buyableId") Long buyableId);

	List<RequirementEntity> findByIdBuyableId(@Param("buyableId") Long buyableId);

	Page<RequirementEntity> findByIdBuyableId(@Param("buyableId") Long buyableId, Pageable pageable);

}
