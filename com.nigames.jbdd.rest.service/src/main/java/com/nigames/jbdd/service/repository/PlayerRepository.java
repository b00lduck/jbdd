package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.PlayerEntity;
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
public interface PlayerRepository extends PagingAndSortingRepository<PlayerEntity, Long> {

	PlayerEntity findByNickname(@Param("nickname") String nickname);


	long countByUserId(@Param("userId") long userId);

	List<PlayerEntity> findByUserId(@Param("userId") long userId);

	Page<PlayerEntity> findByUserId(@Param("userId") long userId, Pageable pageable);


	/*
	long countByUnused();

	Page<PlayerEntity> findUnused();

	Page<PlayerEntity> findUnused(Pageable pageable);
	*/

}
