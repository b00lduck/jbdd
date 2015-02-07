package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.item.TechnologyEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface TechnologyRepository extends PagingAndSortingRepository<TechnologyEntity, Long> {

	List<TechnologyEntity> findByEnabled(boolean enabled);

}
