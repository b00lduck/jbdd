package com.nigames.jbdd.service.config;

import com.wordnik.swagger.jaxrs.config.BeanConfig;
import com.wordnik.swagger.jaxrs.json.JacksonJsonProvider;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 08.02.2015.
 */
@Configuration
public class SwaggerConfig {

	@Bean
	public BeanConfig beanConfig() {

		ResteasyProviderFactory.getInstance().registerProvider(ApiDeclarationProvider.class);
		ResteasyProviderFactory.getInstance().registerProvider(JacksonJsonProvider.class);
		ResteasyProviderFactory.getInstance().registerProvider(ResourceListingProvider.class);

		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("v1");
		beanConfig.setBasePath("http://localhost:8080");
		beanConfig.setResourcePackage("com.nigames.jbdd.rest.api");
		beanConfig.setScan(true);
		return beanConfig;
	}

}

