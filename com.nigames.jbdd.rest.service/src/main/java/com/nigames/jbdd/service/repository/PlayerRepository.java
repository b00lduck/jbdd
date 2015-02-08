package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface PlayerRepository extends PagingAndSortingRepository<PlayerEntity, Long> {

	PlayerEntity findByNickname(String nickname);

	long countByUserId(long userId);

	List<PlayerEntity> findByUserId(long userId);

	Page<PlayerEntity> findByUserId(long userId, Pageable pageable);


	@Query("select p from PlayerEntity p where p.enabled = 1 and p.user is null")
	Page<PlayerEntity> findUnused(Pageable pageable);

	@Query("select count(p.id) from PlayerEntity p where p.enabled = 1 and p.user is null")
	long countUnused();

	/*
	long countByUnused();

	Page<PlayerEntity> findUnused();

	Page<PlayerEntity> findUnused(Pageable pageable);
	*/

}
