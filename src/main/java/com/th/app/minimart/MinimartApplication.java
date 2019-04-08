package com.th.app.minimart;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@EnableTransactionManagement
@EnableJpaRepositories(basePackages="com.th.app.minimart.repository")
@EntityScan(basePackages="com.th.app.minimart.entity")
@ComponentScan(basePackages={"com.th"})
public class MinimartApplication extends SpringBootServletInitializer implements WebApplicationInitializer, CommandLineRunner {
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);
	private static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) {
		SpringApplication.run(MinimartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
}
