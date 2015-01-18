package com.nigames.jbdd.service.config;

import com.nigames.jbdd.service.config.dev.CreateDefaultUsersConfig;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableAsync;

@SuppressWarnings("DuplicateStringLiteralInspection")
@ComponentScan("com.nigames.jbdd.service")
@EnableAsync(mode = AdviceMode.ASPECTJ)
@Import({ApplicationConfigJPA.class,
        ApplicationConfigJTA.class,
        SecurityConfig.class,
        WebConfig.class,
        WebSecurityConfig.class,
        CreateDefaultUsersConfig.class,
        MessageSourceConfig.class})
public class ApplicationConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("com.nigames.jbdd.service.i18n.messages");
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }


}
