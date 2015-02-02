package com.nigames.jbdd.domain.entities.auth;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.facet.CanBeEnabledEntityFacet;
import com.nigames.jbdd.domain.entities.facet.identifyable.IdentifyableEntityFacetImpl;
import com.nigames.jbdd.rest.dto.UserRoleEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Database entity of the Users object.
 *
 * @author Daniel
 */
@SuppressWarnings("StringConcatenationMissingWhitespace")
@Entity
@Table(name = "users")
@NamedQueries({
		@NamedQuery(name = UserEntity.NQ_BY_USERNAME,
				query = "SELECT u FROM UserEntity u WHERE u.username=:username"),
		@NamedQuery(name = UserEntity.NQ_BY_EMAIL,
				query = "SELECT u FROM UserEntity u WHERE u.email=:email"),
		@NamedQuery(
				name = UserEntity.NQ_SORTED_BY_NUM_PLAYERS,
				query = UserEntity.NUM_PLAYERS_SORT_QUERY),
		@NamedQuery(
				name = UserEntity.NQ_SORTED_BY_NUM_PLAYERS_DESC,
				query = UserEntity.NUM_PLAYERS_SORT_QUERY + " DESC")})

public class UserEntity extends IdentifyableEntityFacetImpl implements CanBeEnabledEntityFacet {

	public static final String NQ_SORTED_BY_NUM_PLAYERS = "UserEntity.1";
	public static final String NQ_SORTED_BY_NUM_PLAYERS_DESC = "UserEntity.2";
	public static final String NQ_BY_USERNAME = "UserEntity.3";
	public static final String NQ_BY_EMAIL = "UserEntity.4";

	static final String NUM_PLAYERS_SORT_QUERY =
			"SELECT u FROM UserEntity u LEFT JOIN u.playerList p GROUP BY u.id ORDER BY COUNT(p.id)";
	/**
	 * Max length of the username.
	 */
	private static final int MAX_USERNAME_LENGTH = 32;

	/**
	 * Max length of the encrypted password.
	 */
	private static final int MAX_HASH_LENGTH = 60;

	/**
	 * Max length of the encrypted password.
	 */
	private static final int MIN_HASH_LENGTH = 60;

	/**
	 * Max length of the email address.
	 */
	private static final int MAX_EMAIL_LENGTH = 64;

	/**
	 * enabled flag
	 */
	private boolean enabled;

	/**
	 * The username used to login.
	 */
	@NotNull
	@Column(unique = true)
	@Size(max = MAX_USERNAME_LENGTH)
	private String username;

	/**
	 * The encrypted (hashed) password used to login.
	 */
	@NotNull
	@Size(min = MIN_HASH_LENGTH, max = MAX_HASH_LENGTH)
	private String password;

	/**
	 * The email address of the User.
	 */
	@NotNull
	@Column(unique = true)
	@Size(max = MAX_EMAIL_LENGTH)
	private String email;

	/**
	 * The list of {@link UserRoleEnum} assigned to the User.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable (name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "userRoleList"})
			)
	@Enumerated(EnumType.ORDINAL)
	private List<UserRoleEnum> userRoleList = new ArrayList<>();

	/**
	 * The list of {@link PlayerEntity} objects assigned to the User.
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<PlayerEntity> playerList = new ArrayList<>();

	public final String getUsername() {
		return username;
	}

	public final void setUsername(final String username) {
		this.username = username;
	}

	public final String getPassword() {
		return password;
	}

	public final void setPassword(final String password) {
		this.password = password;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(final String email) {
		this.email = email;
	}

	public final List<UserRoleEnum> getUserRoleList() {
		return userRoleList;
	}

	public final List<PlayerEntity> getPlayerList() {
		return playerList;
	}

	@Override
	public final boolean isEnabled() {
		return enabled;
	}

	@Override
	public final void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

}
