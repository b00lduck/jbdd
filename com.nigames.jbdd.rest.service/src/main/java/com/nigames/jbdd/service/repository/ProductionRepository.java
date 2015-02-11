package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.subitem.ProductionEntity;
import com.nigames.jbdd.domain.entities.subitem.ProductionEntityPK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface ProductionRepository extends PagingAndSortingRepository<ProductionEntity, ProductionEntityPK> {

	long countByIdJobId(Long jobId);

	Page<ProductionEntity> findByIdJobId(Long jobId, Pageable pageable);

	List<ProductionEntity> findByIdJobId(Long jobId);

}
