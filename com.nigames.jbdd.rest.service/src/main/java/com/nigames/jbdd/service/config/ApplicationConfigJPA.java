package com.nigames.jbdd.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.persistence.EntityManager;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class ApplicationConfigJPA {

    @Autowired
    private transient LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Bean
    public EntityManager entityManager() {
        return entityManagerFactoryBean.getObject().createEntityManager();
    }

}
