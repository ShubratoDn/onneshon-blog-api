package com.onneshon.blog.configs.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.onneshon.blog.configs.UserDetailServiceImple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//STEP 2: after Util Class creation.
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailServiceImple userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String reqToken = request.getHeader("Authorization");
		
		String userName= null;
		String token = null;
		
		//jodi token null na hoy 
		if(reqToken != null && reqToken.startsWith("Bearer ")) {		
			token = reqToken.substring(7);			
			//token theke user name fetch korte hobe
			try {
				
				//userName= jwtUtil.extractUsername(token);
				userName= jwtUtil.extractUsername(token);
				
				
			} catch (IllegalArgumentException  e) {
				System.out.println("Unable to get JWT token (Filter Class theke)");
			}catch (ExpiredJwtException e) {
				System.out.println("JWT token has Expired (Filter Class theke)");
			}catch (MalformedJwtException e) {
				System.out.println("Invalid JWT (from Filter Class)");
			}catch(Exception e) {
				System.out.println("USER NAME EXTRACT HOYNA");
			}
			
		}else {			
			System.out.println("Bearer diye start hoyni token ta (from Filter Class)");	
			System.out.println("Token is : " + reqToken);
		}
		
		System.out.println("Filter WORKING 2");
		
		//VALIDATING THE TOKEN		
		if(userName!= null && SecurityContextHolder.getContext().getAuthentication() == null) {
			System.out.println("Filter WORKING 3");
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			System.out.println("Filter WORKING 4");
			if(jwtUtil.validateToken(token, userDetails)) {
				System.out.println("Filter WORKING 5");
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}else {
				System.out.println("JwtAuthenticationFilter : Secure banaite bertho");
			}
			
		}else {
			System.out.println("JwtAuthenticationFilter : User name paisi na");
		}
			
		//***
		filterChain.doFilter(request, response);
		
		
	}	
	
}
