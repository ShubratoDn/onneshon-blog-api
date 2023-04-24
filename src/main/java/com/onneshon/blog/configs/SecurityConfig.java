package com.onneshon.blog.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	// STEP 1: Default Configures of security
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable()
		.authorizeHttpRequests().anyRequest().authenticated()
		.and()
		.httpBasic();

		
		DefaultSecurityFilterChain myConfigure = http.build();
		return myConfigure;
	}

}
