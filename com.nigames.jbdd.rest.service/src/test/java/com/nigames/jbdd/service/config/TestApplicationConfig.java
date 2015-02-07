package com.nigames.jbdd.service.config;

import com.nigames.jbdd.service.config.dev.CreateDefaultUsersConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(value = {"com.nigames.jbdd.service"},
        excludeFilters = {
                //   @ComponentScan.Filter(type = FilterType.ASPECTJ,
                //           pattern = "com.nigames.jbdd.service.beans.application.*"),
                @ComponentScan.Filter(type = FilterType.ASPECTJ,
                        pattern = "com.nigames.jbdd.service.config.*")
        })
@Import({TestApplicationConfigJPA.class,
        TestApplicationConfigJTA.class,
        TestDataConfigH2.class,
        CreateDefaultUsersConfig.class,
        WebConfig.class,
        SecurityConfig.class,
        WebSecurityConfig.class,
        MessageSourceConfig.class})
@EnableAutoConfiguration
@EnableJpaRepositories("com.nigames.jbdd.service.repository")
@EnableAsync(mode = AdviceMode.ASPECTJ)
public class TestApplicationConfig {

}
