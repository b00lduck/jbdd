package com.nigames.jbdd.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;

public class TestApplicationConfigJPA {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactory.getObject().createEntityManager();
    }

}
