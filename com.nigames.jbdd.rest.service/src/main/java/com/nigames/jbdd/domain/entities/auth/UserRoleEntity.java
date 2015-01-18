package com.nigames.jbdd.domain.entities.auth;

import com.nigames.jbdd.domain.entities.aspect.identifyable.IdentifyableEntityAspect;

import javax.persistence.*;

/**
 * Database entity for UserRoles.
 *
 * @author Daniel
 */
@SuppressWarnings("InstanceVariableMayNotBeInitialized")
@Entity
@Table(name = "user_role")
@NamedQueries(
        @NamedQuery(name = "findUserRoleByRoleAndUser",
                query = "SELECT u FROM UserRoleEntity u WHERE u.role=:role AND u.user=:user")
)

public class UserRoleEntity implements IdentifyableEntityAspect {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Version
    private int version;

    /**
     * The user role as String i.e. "USER_ADMIN".
     */
    private String role;

    /**
     * The User object.
     */
    @ManyToOne
    private UserEntity user;

    /**
     * Create with role and user given.
     *
     * @param role The role
     * @param user The user
     */
    public static UserRoleEntity createFromRoleAndUser(final String role, final UserEntity user) {
        final UserRoleEntity instance = new UserRoleEntity();
        instance.setRole(role);
        instance.setUser(user);
        return instance;
    }

    public final String getRole() {
        return role;
    }

    public final void setRole(final String role) {
        this.role = role;
    }

    public final UserEntity getUser() {
        return user;
    }

    final void setUser(final UserEntity user) {
        this.user = user;
    }

    @Override
    public final long getId() {
        return id;
    }

    @SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserRoleEntity)) {
            return false;
        }

        final UserRoleEntity that = (UserRoleEntity) o;

        if (!role.equals(that.role)) {
            return false;
        }
        return user.equals(that.user);

    }

    @Override
    public int hashCode() {
        int result = role.hashCode();
        result = (31 * result) + user.hashCode();
        return result;
    }

}
