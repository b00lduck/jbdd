package com.nigames.jbdd.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class MessageSourceConfig {

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        final ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("com.nigames.jbdd.service.i18n.messages");
        messageSource.setFallbackToSystemLocale(false);
        return messageSource;
    }


}
