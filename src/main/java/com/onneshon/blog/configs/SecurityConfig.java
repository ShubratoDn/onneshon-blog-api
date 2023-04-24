package com.onneshon.blog.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

		//step 5: http ke boltesi j Data authentication hobe Database theke
		http.authenticationProvider(this.authenticationProvider());
		
		
		DefaultSecurityFilterChain myConfigure = http.build();
		return myConfigure;
	}
	
	
	@Autowired
	private UserDetailServiceImple userDetailsServiceImple;
	
	//step 6:
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsServiceImple);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
	
	
	//step 7: password encoder banano
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	

}
