package com.nigames.jbdd.service.beans.application;

import com.nigames.jbdd.domain.entities.PlayerEntity;
import com.nigames.jbdd.domain.entities.auth.UserEntity;
import com.nigames.jbdd.rest.dto.Player;
import com.nigames.jbdd.rest.dto.User;
import com.nigames.jbdd.rest.dto.UserRoleEnum;
import com.nigames.jbdd.service.service.player.PlayerService;
import com.nigames.jbdd.service.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Abstract base bean that checks for existence of specific users in the database. User for admin
 * users etc.
 *
 * @author Daniel
 */
@SuppressWarnings({"ClassWithTooManyMethods", "DynamicRegexReplaceableByCompiledPattern", "SpringJavaAutowiredMembersInspection", "CallToSimpleGetterFromWithinClass", "AbstractClassWithoutAbstractMethods"})
public abstract class AbstractCheckUserBean {

	private static final Logger LOG = LoggerFactory
			.getLogger(AbstractCheckUserBean.class);

	private static final String PARAM_MARKER = "\\{param\\}";

	/**
	 * The user service.
	 */
	@SuppressWarnings("ProtectedField")
	@Autowired
	protected UserService userService;

	/**
	 * The playerSubItem service.
	 */
	@Autowired
	private PlayerService playerService;

	/**
	 * The username used for checking and possible creation of the {@link User) object.
	 */
	private String username;

	/**
	 * The password used for checking and possible creation of the {@link User) object.
	 */
	private String password;

	/**
	 * The email used for checking and possible creation of the {@link UserEntity} object.
	 */
	private String email;

	/**
	 * The nickname used for checking and possible creation of the {@link PlayerEntity} object.
	 */
	private String nickname;

	@Transactional
	void checkUserInactive() {
		checkUserInactive(getUsername());
	}

	@Transactional
	void checkUserInactive(final String username) {
		LOG.info("Checking if playerSubItem user \"{}\" is disabled", username);
		final User user = userService.findByUsername(username);
		if (user.isEnabled()) {
			user.setEnabled(Boolean.FALSE);
			userService.update(user.getId(), user);
		}
	}

	@Transactional
	void checkUserActive() {
		checkUserActive(getUsername());
	}

	@Transactional
	void checkUserActive(final String username) {
		LOG.info("Checking if playerSubItem user \"{}\" is enabled", username); // NON-NLS
		final User user = userService.findByUsername(username);
		if (!user.isEnabled()) {
			user.setEnabled(Boolean.TRUE);
			userService.update(user.getId(), user);
		}
	}

	@Transactional
	protected void checkPlayerActive() {
		checkPlayerActive(getNickname());
	}

	@Transactional
	void checkPlayerActive(final String nickname) {
		LOG.info("Checking if playerSubItem \"{}\" is enabled", nickname); // NON-NLS
		final Player player = playerService.findByNickname(nickname);

		if (!player.isEnabled()) {
			player.setEnabled(Boolean.TRUE);
			playerService.update(player.getId(), player);
		}
	}

	@Transactional
	void checkPlayerInactive() {
		checkPlayerInactive(getNickname());
	}

	@Transactional
	void checkPlayerInactive(final String nickname) {
		LOG.info("Checking if playerSubItem \"{}\" is disabled", nickname); // NON-NLS
		final Player player = playerService.findByNickname(nickname);

		if ((player != null) && (player.isEnabled())) {
			player.setEnabled(Boolean.FALSE);
			playerService.update(player.getId(), player);
		}
	}

	@Transactional
	void checkUserExists(final Boolean enabled) {
		checkUserExists(getUsername(), getPassword(), getEmail(), enabled);
	}

	@Transactional
	void checkUserExists(final String username, final String password,
	                     final String email, final Boolean enabled) {
		LOG.info("Checking if user \"{}\" exists", username); // NON-NLS

		if (!userService.isUsernameExisting(username)) {
			final User newUser = new User();
			newUser.setUsername(username);
			newUser.setEnabled(enabled);
			newUser.setPassword(password);
			newUser.setEmail(email);
			userService.create(newUser, false);
		}

	}

	@Transactional
	void checkPlayerExists(final Boolean enabled) {
		checkPlayerExists(getNickname(), getUsername(), enabled);
	}

	@Transactional
	void checkPlayerExists(final String nickname, final String playerUsername,
	                       final Boolean enabled) {
		LOG.info("Checking if dummy playerSubItem user \"{}\" has playerSubItem with nickname \"{}\"", playerUsername, nickname); // NON-NLS
		final User user = userService.findByUsername(playerUsername);

		final List<Player> playerList = playerService.findByUserId(user.getId());

		Player foundPlayer = null;
		for (final Player player : playerList) {
			final String otherNickname = player.getNickname();
			if (nickname.equals(otherNickname)) {
				foundPlayer = player;
				//noinspection BreakStatement
				break;
			}
		}

		if (null == foundPlayer) {
			final Player newPlayer = new Player();
			newPlayer.setNickname(nickname);
			newPlayer.setEnabled(enabled);

			final Player createdPlayer = playerService.create(newPlayer);
			userService.addPlayer(user.getId(), createdPlayer.getId());
		}
	}

	@SuppressWarnings("StringConcatenation")
	@Transactional
	void checkAdminRoles() {
		LOG.info("Setting roles of the user \"{}\"", getUsername()); // NON-NLS
		final User user = userService.findByUsername(getUsername());
		userService.removeAllRoles(user.getId());
		for(final UserRoleEnum ur : UserRoleEnum.values()) {
			userService.addRole(user.getId(), ur);
		}
	}

	@Transactional
	void checkPlayerRoles() {
		LOG.info("Setting roles of the user \"{}\" to {}", getUsername(), UserRoleEnum.ROLE_PLAYER); // NON-NLS
		final User user = userService.findByUsername(getUsername());
		userService.addRole(user.getId(), UserRoleEnum.ROLE_PLAYER);
	}

	String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	String getUsername(final String param) {
		return getUsername().replaceAll(PARAM_MARKER, param);
	}

	String getPassword() {
		return password;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	String getPassword(final String param) {
		return getPassword().replaceAll(PARAM_MARKER, param);
	}

	String getNickname(final String param) {
		return getNickname().replaceAll(PARAM_MARKER, param);
	}

	String getNickname() {
		return nickname;
	}

	public void setNickname(final String dummyPlayerNickname) {
		nickname = dummyPlayerNickname;
	}

	String getEmail() {
		return email;
	}

	public void setEmail(final String dummyPlayerEmail) {
		email = dummyPlayerEmail;
	}

	String getEmail(final String param) {
		return getEmail().replaceAll(PARAM_MARKER, param);
	}

}
