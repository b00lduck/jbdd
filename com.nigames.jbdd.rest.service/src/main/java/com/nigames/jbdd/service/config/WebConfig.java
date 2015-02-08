package com.nigames.jbdd.service.config;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

	@SuppressWarnings("RefusedBequest")
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/app/**").addResourceLocations("classpath:/app/");
		registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/swagger-ui/");
	}

}




