package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntity;
import com.nigames.jbdd.domain.entities.subitem.buyable.CostEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface CostRepository extends PagingAndSortingRepository<CostEntity, CostEntityPK> {

	long countByIdBuyableId(Long buyableId);

	Page<CostEntity> findByIdBuyableId(Long buyableId, Pageable pageable);

	List<CostEntity> findByIdBuyableId(Long buyableId);

}
