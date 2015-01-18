package com.nigames.jbdd;

import com.nigames.jbdd.service.config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@SuppressWarnings("ALL")
@Configuration
@EnableAutoConfiguration
@Import(ApplicationConfig.class)
public class Boot {
    public static void main(final String[] args) {
        SpringApplication.run(Boot.class, args);
    }
}





