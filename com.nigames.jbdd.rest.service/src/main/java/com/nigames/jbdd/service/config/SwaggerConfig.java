package com.nigames.jbdd.service.config;

import com.wordnik.swagger.jaxrs.config.BeanConfig;
import com.wordnik.swagger.jaxrs.json.JacksonJsonProvider;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import javax.ws.rs.Path;

/**
 * Configuration for swagger within RestEasy.
 * This registers the appropiate swagger providers and a JAX-RS endpoint "/api-docs".
 *
 * This file is part of JBdD by nigames.de
 * <p>
 * Created by Daniel on 08.02.2015.
 */
@Configuration
@ComponentScan(basePackageClasses = ApiListingResourceJSON.class,
		includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = Path.class))
public class SwaggerConfig {

	/**
	 * Configuration bean for swagger.
	 */
	@Bean
	public BeanConfig swaggerBeanConfig() {

		registerSwaggerJaxRsProviders();

		final BeanConfig swaggerBeanConfig = new BeanConfig();
		swaggerBeanConfig.setVersion("1.0.0");
		swaggerBeanConfig.setDescription("RESTful services API for \"Das Buch des Drachen\"");
		swaggerBeanConfig.setTitle("JBdD REST API");
		swaggerBeanConfig.setContact("jbdd@nigames.com");
		swaggerBeanConfig.setBasePath("http://localhost:8080");
		swaggerBeanConfig.setResourcePackage("com.nigames.jbdd.rest.api");
		swaggerBeanConfig.setScan(true);
		return swaggerBeanConfig;
	}

	/**
	 * Register the swagger providers within the RestEasy framework.
	 */
	private void registerSwaggerJaxRsProviders() {
		final ResteasyProviderFactory resteasyProviderFactory = ResteasyProviderFactory.getInstance();

		resteasyProviderFactory.registerProvider(ApiDeclarationProvider.class);
		resteasyProviderFactory.registerProvider(JacksonJsonProvider.class);
		resteasyProviderFactory.registerProvider(ResourceListingProvider.class);
	}


}

