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
@Entity
@Table(name = "users")
public final class UserEntity extends IdentifyableEntityFacetImpl implements CanBeEnabledEntityFacet {

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
	 * The list of {@link UserRoleEnum} assigned to the User.
	 */
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "user_roles",
			joinColumns = @JoinColumn(name = "user_id"),
			uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "userRoleList"}))
	@Enumerated(EnumType.ORDINAL)
	private final List<UserRoleEnum> userRoleList = new ArrayList<>();
	/**
	 * The list of {@link PlayerEntity} objects assigned to the User.
	 */
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private final List<PlayerEntity> playerList = new ArrayList<>();
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

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public List<UserRoleEnum> getUserRoleList() {
		return userRoleList;
	}

	public List<PlayerEntity> getPlayerList() {
		return playerList;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(final boolean enabled) {
		this.enabled = enabled;
	}

    @Override
    protected boolean isEqual(final Object object) {
        return object instanceof UserEntity;
    }

}
