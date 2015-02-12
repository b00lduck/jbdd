package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.RequirementEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface RequirementRepository extends PagingAndSortingRepository<RequirementEntity, RequirementEntityPK> {

	long countByIdBuyableId(Long buyableId);

	List<RequirementEntity> findByIdBuyableId(Long buyableId);

	Page<RequirementEntity> findByIdBuyableId(Long buyableId, Pageable pageable);

}
