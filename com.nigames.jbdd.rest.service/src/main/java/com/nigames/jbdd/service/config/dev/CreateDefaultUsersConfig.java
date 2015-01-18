package com.nigames.jbdd.service.config.dev;

import com.nigames.jbdd.service.beans.application.CheckAdminUserBean;
import com.nigames.jbdd.service.beans.application.CheckDefaultPlayerBean;
import com.nigames.jbdd.service.beans.application.CheckDummyPlayerBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SuppressWarnings("ClassWithTooManyFields")
@PropertySource("classpath:config.properties")
public class CreateDefaultUsersConfig {

    // admin
    @Value("#{ environment['config.auth.defaultAdminUsername'] }")
    private transient String defaultAdminUsername;

    @Value("#{ environment['config.auth.defaultAdminPassword'] }")
    private transient String defaultAdminPassword;

    @Value("#{ environment['config.auth.defaultAdminEmail'] }")
    private transient String defaultAdminEmail;

    // default
    @Value("#{ environment['config.auth.defaultPlayerUsername'] }")
    private transient String defaultPlayerUsername;

    @Value("#{ environment['config.auth.defaultPlayerPassword'] }")
    private transient String defaultPlayerPassword;

    @Value("#{ environment['config.auth.defaultPlayerNickname'] }")
    private transient String defaultPlayerNickname;

    @Value("#{ environment['config.auth.defaultPlayerEmail'] }")
    private transient String defaultPlayerEmail;

    // dummies
    @Value("#{ environment['config.auth.dummyPlayerUsername'] }")
    private transient String dummyPlayerUsername;

    @Value("#{ environment['config.auth.dummyPlayerPassword'] }")
    private transient String dummyPlayerPassword;

    @Value("#{ environment['config.auth.dummyPlayerNickname'] }")
    private transient String dummyPlayerNickname;

    @Value("#{ environment['config.auth.dummyPlayerEmail'] }")
    private transient String dummyPlayerEmail;

    @Value("#{ environment['config.auth.numberOfDummyPlayers'] }")
    private transient Integer numberOfDummyPlayers;

    /**
     * Configures the CheckAdminUserBean.
     *
     * @return CheckAdminUserBean instance
     */
    @Bean
    public CheckAdminUserBean checkAdminUserBean() {
        final CheckAdminUserBean bean = new CheckAdminUserBean();
        bean.setUsername(defaultAdminUsername);
        bean.setPassword(defaultAdminPassword);
        bean.setEmail(defaultAdminEmail);
        return bean;
    }

    /**
     * Configures the CheckDefaultPlayerBean.
     *
     * @return CheckAdminUserBean instance
     */
    @Bean
    public CheckDefaultPlayerBean checkDefaultPlayerBean() {
        final CheckDefaultPlayerBean bean = new CheckDefaultPlayerBean();
        bean.setUsername(defaultPlayerUsername);
        bean.setPassword(defaultPlayerPassword);
        bean.setNickname(defaultPlayerNickname);
        bean.setEmail(defaultPlayerEmail);
        return bean;
    }

    /**
     * Configures the CheckDummyPlayerBean.
     *
     * @return CheckDummyPlayerBean instance
     */
    @Bean
    public CheckDummyPlayerBean checkDummyPlayerBean() {
        final CheckDummyPlayerBean bean = new CheckDummyPlayerBean();
        bean.setUsername(dummyPlayerUsername);
        bean.setPassword(dummyPlayerPassword);
        bean.setNickname(dummyPlayerNickname);
        bean.setEmail(dummyPlayerEmail);
        bean.setNumberOfDummyPlayers(numberOfDummyPlayers);
        return bean;
    }

}
