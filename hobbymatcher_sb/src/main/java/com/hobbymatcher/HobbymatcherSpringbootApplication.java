package com.hobbymatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

//exclude = SecurityAutoConfiguration.class
@SpringBootApplication
@EnableSwagger2
@RestController
public class HobbymatcherSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HobbymatcherSpringbootApplication.class, args);
	}
}
