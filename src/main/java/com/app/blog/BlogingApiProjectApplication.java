package com.app.blog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@SpringBootApplication() 
public class BlogingApiProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogingApiProjectApplication.class, args);
		
		
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	
	
	
}
