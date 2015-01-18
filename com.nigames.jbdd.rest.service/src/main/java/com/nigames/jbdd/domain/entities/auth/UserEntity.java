package com.nigames.jbdd.domain.entities.auth;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.aspect.CanBeEnabledEntityAspect;
import com.nigames.jbdd.domain.entities.aspect.identifyable.IdentifyableEntityAspectImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

import static com.nigames.jbdd.rest.dto.UserRole.*;

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
        @NamedQuery(name = UserEntity.NQ_SORTED_BY_ADMIN_USER_ROLE,
                query = UserEntity.ROLE_FILTER_QUERY + ROLE_ADMIN_USER + "' ORDER BY p.role"),
        @NamedQuery(name = UserEntity.NQ_SORTED_BY_ADMIN_USER_ROLE_DESC,
                query = UserEntity.ROLE_FILTER_QUERY + ROLE_ADMIN_USER + "' ORDER BY p.role DESC"),
        @NamedQuery(name = UserEntity.NQ_SORTED_BY_ADMIN_PLAYER_ROLE,
                query = UserEntity.ROLE_FILTER_QUERY + ROLE_ADMIN_PLAYER + "' ORDER BY p.role"),
        @NamedQuery(name = UserEntity.NQ_SORTED_BY_ADMIN_PLAYER_ROLE_DESC,
                query = UserEntity.ROLE_FILTER_QUERY + ROLE_ADMIN_PLAYER + "' ORDER BY p.role DESC"),
        @NamedQuery(name = UserEntity.NQ_SORTED_BY_PLAYER_ROLE,
                query = UserEntity.ROLE_FILTER_QUERY + ROLE_PLAYER + "' ORDER BY p.role"),
        @NamedQuery(name = UserEntity.NQ_SORTED_BY_PLAYER_ROLE_DESC,
                query = UserEntity.ROLE_FILTER_QUERY + ROLE_PLAYER + "' ORDER BY p.role DESC"),
        @NamedQuery(
                name = UserEntity.NQ_SORTED_BY_NUM_PLAYERS,
                query = UserEntity.NUM_PLAYERS_SORT_QUERY),
        @NamedQuery(
                name = UserEntity.NQ_SORTED_BY_NUM_PLAYERS_DESC,
                query = UserEntity.NUM_PLAYERS_SORT_QUERY + " DESC")})

public class UserEntity extends IdentifyableEntityAspectImpl implements CanBeEnabledEntityAspect {

    public static final String NQ_BY_USERNAME = "UserEntity.findUserByUsername";
    public static final String NQ_BY_EMAIL = "UserEntity.findUserByEmail";
    public static final String NQ_SORTED_BY_ADMIN_USER_ROLE = "UserEntity.findAllSortedByAdminUserRole";
    public static final String NQ_SORTED_BY_ADMIN_USER_ROLE_DESC = "UserEntity.findAllSortedByAdminUserRoleDesc";
    public static final String NQ_SORTED_BY_ADMIN_PLAYER_ROLE = "UserEntity.findAllSortedByAdminPlayerRole";
    public static final String NQ_SORTED_BY_ADMIN_PLAYER_ROLE_DESC = "UserEntity.findAllSortedByAdminPlayerRoleDesc";
    public static final String NQ_SORTED_BY_PLAYER_ROLE = "UserEntity.findAllSortedByPlayerRole";
    public static final String NQ_SORTED_BY_PLAYER_ROLE_DESC = "UserEntity.findAllSortedByPlayerRoleDesc";
    public static final String NQ_SORTED_BY_NUM_PLAYERS = "UserEntity.findAllSortedByNumPlayers";
    public static final String NQ_SORTED_BY_NUM_PLAYERS_DESC = "UserEntity.findAllSortedByNumPlayersDesc";
    @SuppressWarnings("HardCodedStringLiteral")
    static final String ROLE_FILTER_QUERY =
            "SELECT u FROM UserEntity u LEFT JOIN u.userRoleList p WITH p.role='";
    @SuppressWarnings("HardCodedStringLiteral")
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
     * The list of {@link UserRoleEntity} objects assigned to the User.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserRoleEntity> userRoleList = new ArrayList<>();

    /**
     * The list of {@link PlayerEntity} objects assigned to the User.
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<PlayerEntity> playerList = new ArrayList<>();

    /**
     * Return the type name "User".
     *
     * @return always "User"
     */
    @SuppressWarnings("SameReturnValue")
    public static String getType() {
        return "User";
    }

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

    public final List<UserRoleEntity> getUserRoleList() {
        return userRoleList;
    }

    public final void setUserRoleList(final List<UserRoleEntity> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public final List<PlayerEntity> getPlayerList() {
        return playerList;
    }

    public final void setPlayerList(final List<PlayerEntity> playerList) {
        this.playerList = playerList;
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
