package com.nigames.jbdd.rest.dto;

/**
 * @Author Daniel Zerlett
 * 2015-02-02
 */
public enum UserRoleEnum {

	/**
	 * System role. A user may not have this role at all.
	 */
	ROLE_SYSTEM,

	/**
	 * User may have player objects assigned.
	 */
	ROLE_PLAYER,

	/**
	 * User may administrate players.
	 */
	ROLE_ADMIN_USER,

	/**
	 * User may administrate players.
	 */
	ROLE_ADMIN_PLAYER,

	/**
	 * User may administrate buildings.
	 */
	ROLE_ADMIN_BUILDING,

	/**
	 * User may administrate technologies.
	 */
	ROLE_ADMIN_STORAGETYPE,

	/**
	 * User may administrate storagetypes.
	 */
	ROLE_ADMIN_TECHNOLOGY,

	/**
	 * User may administrate jobs.
	 */
	ROLE_ADMIN_JOB,

	/**
	 * User may administrate goods.
	 */
	ROLE_ADMIN_GOOD,

	/**
	 * User may administrate costs of buyables.
	 */
	ROLE_ADMIN_BUYABLE_COST,

	/**
	 * User may administrate requirements of buyables.
	 */
	ROLE_ADMIN_BUYABLE_REQUIREMENT,

	/**
	 * User may administrate production of jobs..
	 */
	ROLE_ADMIN_JOB_PRODUCTION

}
