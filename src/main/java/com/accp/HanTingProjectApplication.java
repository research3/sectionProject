package com.accp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class HanTingProjectApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(HanTingProjectApplication.class, args);
	}
	
	@Override
	public SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(HanTingProjectApplication.class);
	}

}

