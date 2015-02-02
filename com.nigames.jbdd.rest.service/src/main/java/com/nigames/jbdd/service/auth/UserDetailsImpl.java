package com.nigames.jbdd.service.auth;

import com.nigames.jbdd.rest.dto.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 5702593436010041766L;

	private final String username;

	private final String password;

	private final Boolean enabled;

	private final List<Authority> authorities;

	UserDetailsImpl(final User user) {
		username = user.getUsername();
		password = user.getPassword();
		enabled = user.isEnabled();
		authorities = new ArrayList<>();
		authorities.addAll(user.getRoles().stream().map(Authority::new).collect(Collectors.toList()));
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

}
