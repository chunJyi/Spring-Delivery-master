package com.jdc.spring.delivery;

import com.jdc.spring.delivery.controller.common.CategoryFormatter;
import com.jdc.spring.delivery.controller.common.LocalDateFormatter;
import com.jdc.spring.delivery.controller.common.LocalDateTimeFormatter;
import com.jdc.spring.delivery.controller.common.LocalTimeFormatter;
import com.jdc.spring.delivery.repo.custom.DeliveryBaseRepository;
import com.jdc.spring.delivery.views.JasperViewResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@EnableJpaRepositories(repositoryBaseClass = DeliveryBaseRepository.class)
public class ConfigAboutMVC implements WebMvcConfigurer {

	@Autowired
	private CategoryFormatter categoryFormatter;
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/home");
		registry.addViewController("/login").setViewName("/login");
		registry.addViewController("/signup").setViewName("/signup");
	}
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(categoryFormatter);
		registry.addFormatter(new LocalDateFormatter());
		registry.addFormatter(new LocalDateTimeFormatter());
		registry.addFormatter(new LocalTimeFormatter());
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.viewResolver(new JasperViewResolver());
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
			.addResourceLocations("classpath:/static/");
	}
}
