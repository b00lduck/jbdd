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
    private String defaultAdminUsername;

    @Value("#{ environment['config.auth.defaultAdminPassword'] }")
    private String defaultAdminPassword;

    @Value("#{ environment['config.auth.defaultAdminEmail'] }")
    private String defaultAdminEmail;

    // default
    @Value("#{ environment['config.auth.defaultPlayerUsername'] }")
    private String defaultPlayerUsername;

    @Value("#{ environment['config.auth.defaultPlayerPassword'] }")
    private String defaultPlayerPassword;

    @Value("#{ environment['config.auth.defaultPlayerNickname'] }")
    private String defaultPlayerNickname;

    @Value("#{ environment['config.auth.defaultPlayerEmail'] }")
    private String defaultPlayerEmail;

    // dummies
    @Value("#{ environment['config.auth.dummyPlayerUsername'] }")
    private String dummyPlayerUsername;

    @Value("#{ environment['config.auth.dummyPlayerPassword'] }")
    private String dummyPlayerPassword;

    @Value("#{ environment['config.auth.dummyPlayerNickname'] }")
    private String dummyPlayerNickname;

    @Value("#{ environment['config.auth.dummyPlayerEmail'] }")
    private String dummyPlayerEmail;

    @Value("#{ environment['config.auth.numberOfDummyPlayers'] }")
    private Integer numberOfDummyPlayers;

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
