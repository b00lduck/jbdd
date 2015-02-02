package com.nigames.jbdd.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@SuppressWarnings("SpringJavaAutowiredMembersInspection")
@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)
public class ApplicationConfigJTA implements TransactionManagementConfigurer {

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Bean
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }

}
