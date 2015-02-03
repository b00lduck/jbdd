package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.item.StoragetypeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface StoragetypeRepository extends PagingAndSortingRepository<StoragetypeEntity, Long> {

}
