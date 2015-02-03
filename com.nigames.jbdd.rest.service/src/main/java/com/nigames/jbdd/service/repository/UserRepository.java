package com.nigames.jbdd.service.repository;

import com.nigames.jbdd.domain.entities.auth.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 03.02.2015.
 */
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

	UserEntity findByUsername(@Param("username") String username);

	UserEntity findByEmail(@Param("email") String email);

}
