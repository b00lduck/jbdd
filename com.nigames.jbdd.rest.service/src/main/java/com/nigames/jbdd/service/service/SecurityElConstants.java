package com.nigames.jbdd.service.service;

/**
 * Created by dzerle on 02.02.15.
 */
public final class SecurityElConstants {

	public static final String FORBID_ALL = "hasRole('ROLE_EXCLUDE_ALL')";
	private static final String ROLE_SYSTEM =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_SYSTEM";
	public static final String HAS_ROLE_SYSTEM =
			"hasRole(" + ROLE_SYSTEM + ")";
	private static final String ROLE_ADMIN_USER =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_USER";
	public static final String HAS_ROLE_SYSTEM_OR_ADMIN_USER =
			"hasAnyRole(" + ROLE_SYSTEM + "," + ROLE_ADMIN_USER + ")";
	public static final String HAS_ROLE_ADMIN_USER =
			"hasRole(" + ROLE_ADMIN_USER + ")";
	private static final String ROLE_ADMIN_PLAYER =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_PLAYER";
	public static final String HAS_ROLE_SYSTEM_OR_ADMIN_PLAYER =
			"hasAnyRole(" + ROLE_SYSTEM + "," + ROLE_ADMIN_PLAYER + ")";

	public static final String HAS_ROLE_ADMIN_PLAYER =
			"hasRole(" + ROLE_ADMIN_PLAYER + ")";

	private static final String ROLE_ADMIN_TECHNOLOGY =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_TECHNOLOGY";

	public static final String HAS_ROLE_SYSTEM_OR_ADMIN_TECHNOLOGY =
			"hasAnyRole(" + ROLE_SYSTEM + "," + ROLE_ADMIN_TECHNOLOGY + ")";

	public static final String HAS_ROLE_ADMIN_TECHNOLOGY =
			"hasRole(" + ROLE_ADMIN_TECHNOLOGY + ")";

	private static final String ROLE_ADMIN_BUILDING =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_BUILDING";

	public static final String HAS_ROLE_SYSTEM_OR_ADMIN_BUILDING =
			"hasAnyRole(" + ROLE_SYSTEM + "," + ROLE_ADMIN_BUILDING + ")";

	public static final String HAS_ROLE_ADMIN_BUILDING =
			"hasRole(" + ROLE_ADMIN_BUILDING + ")";

	private static final String ROLE_ADMIN_BUILDING_PRODUCTION =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_BUILDING_PRODUCTION";

	public static final String HAS_ROLE_ADMIN_BUILDING_PRODUCTION =
			"hasRole(" + ROLE_ADMIN_BUILDING_PRODUCTION + ")";


	private static final String ROLE_ADMIN_GOOD =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_GOOD";

	public static final String HAS_ROLE_SYSTEM_OR_ADMIN_GOOD =
			"hasAnyRole(" + ROLE_SYSTEM + "," + ROLE_ADMIN_GOOD + ")";

	public static final String HAS_ROLE_ADMIN_GOOD =
			"hasRole(" + ROLE_ADMIN_GOOD + ")";

	private static final String ROLE_ADMIN_JOB =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_JOB";

	public static final String HAS_ROLE_SYSTEM_OR_ADMIN_JOB =
			"hasAnyRole(" + ROLE_SYSTEM + "," + ROLE_ADMIN_JOB + ")";

	public static final String HAS_ROLE_ADMIN_JOB =
			"hasRole(" + ROLE_ADMIN_JOB + ")";

	private static final String ROLE_ADMIN_STORAGETYPE =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_STORAGETYPE";

	public static final String HAS_ROLE_ADMIN_STORAGETYPE =
			"hasRole(" + ROLE_ADMIN_STORAGETYPE + ")";

	private static final String ROLE_ADMIN_BUYABLE_COST =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_BUYABLE_COST";

	public static final String HAS_ROLE_ADMIN_BUYABLE_COST =
			"hasRole(" + ROLE_ADMIN_BUYABLE_COST + ")";

	private static final String ROLE_ADMIN_BUYABLE_REQUIREMENT =
			"T(com.nigames.jbdd.rest.dto.UserRoleEnum).ROLE_ADMIN_BUYABLE_REQUIREMENT";

	public static final String HAS_ROLE_ADMIN_BUYABLE_REQUIREMENT =
			"hasRole(" + ROLE_ADMIN_BUYABLE_REQUIREMENT + ")";

}
