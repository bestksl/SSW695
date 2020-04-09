package com.hobbymatcher;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

//import com.hobbymatcher.properties.SecurityProperties;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//exclude = SecurityAutoConfiguration.class
@SpringBootApplication
@EnableSwagger2
@RestController
//@EnableConfigurationProperties(SecurityProperties.class)
public class HobbymatcherSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HobbymatcherSpringbootApplication.class, args);
	}

	@Bean // Fix the CORS errors
	public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		// *** URL below needs to match the Vue client URL and port ***
		config.setAllowedOrigins(Arrays.asList("*")); // http://localhost:808x
		config.setAllowedMethods(Arrays.asList("*"));
		config.setAllowedHeaders(Arrays.asList("*"));
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>();
		bean.setFilter(new CorsFilter(source));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
