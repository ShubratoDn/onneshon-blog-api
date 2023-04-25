package com.onneshon.blog.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.SecurityFilterChain;

import com.onneshon.blog.configs.jwt.JwtAuthenticationEntryPoint;
import com.onneshon.blog.configs.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntryPoint authEntryPoint;
	
	@Autowired
	private JwtAuthenticationFilter authFilter;

	// STEP 1: Default Configures of security
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.cors().and().csrf().disable()
		.authorizeHttpRequests().anyRequest().authenticated()
		.and()
		//JWT Config er somoy korsi eta
		.exceptionHandling().authenticationEntryPoint(authEntryPoint)
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		.and().httpBasic() //basic login er jonno
		
		
		//JWT STEP :
		http.addFilterBefore(authFilter, JwtAuthenticationFilter.class);
		
		
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
	
	
	
	//JWT STEP
	//eta kaj hocche User login er 
	@Bean
	AuthenticationManager authenticationManagerBean () throws Exception {
		return new AuthenticationManager() {			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}
	

}
