package com.nigames.jbdd.service.config;

import com.wordnik.swagger.jaxrs.config.BeanConfig;
import org.jboss.resteasy.plugins.spring.SpringBeanProcessor;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.jboss.resteasy.springmvc.ResteasyHandlerAdapter;
import org.jboss.resteasy.springmvc.ResteasyHandlerMapping;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.ws.rs.ext.Provider;

@ComponentScan(value = "com.nigames.jbdd.service.rest", includeFilters = @ComponentScan.Filter(
		type = FilterType.ANNOTATION, value = Provider.class))
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@SuppressWarnings("RefusedBequest")
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/app/**").addResourceLocations("classpath:/app/");
	}

	@ConfigurationProperties(prefix = "resteasy.deployment")
	@Bean(initMethod = "start", destroyMethod = "stop")
	public ResteasyDeployment resteasyDeployment(final SpringBeanProcessor springBeanProcessor) {
		final ResteasyDeployment resteasyDeployment = new ResteasyDeployment() {
			public void start() {
				super.start();
				if (springBeanProcessor.getRegistry() == null) {
					springBeanProcessor.setRegistry(getRegistry());
				}
			}
		};

		final ResteasyProviderFactory providerFactory = springBeanProcessor.getProviderFactory();
		resteasyDeployment.setProviderFactory(providerFactory);
		return resteasyDeployment;
	}

	@Bean
	public SpringBeanProcessor springBeanProcessor() {
		final SpringBeanProcessor springBeanProcessor = new SpringBeanProcessor();
		final ResteasyProviderFactory providerFactory = new ResteasyProviderFactory();
		springBeanProcessor.setProviderFactory(providerFactory);
		return springBeanProcessor;
	}

	@Bean
	public ResteasyHandlerMapping resteasyHandlerMapper(final ResteasyDeployment deployment) {
		final ResteasyHandlerMapping handlerMapping = new ResteasyHandlerMapping(deployment);
		handlerMapping.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
		return handlerMapping;
	}

	@Bean
	public ResteasyHandlerAdapter resteasyHandlerAdapter(final ResteasyDeployment deployment) {
		return new ResteasyHandlerAdapter(deployment);
	}

	@Bean
	public BeanConfig beanConfig() {
		BeanConfig beanConfig = new BeanConfig();
		beanConfig.setVersion("v1");
		beanConfig.setBasePath("http://localhost:8080");
		beanConfig.setResourcePackage("com.nigames.jbdd.rest.api");
		beanConfig.setScan(true);
		return beanConfig;
	}

}




