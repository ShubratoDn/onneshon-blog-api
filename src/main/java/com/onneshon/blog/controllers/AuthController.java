package com.onneshon.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.onneshon.blog.configs.UserDetailServiceImple;
import com.onneshon.blog.configs.jwt.JwtUtil;
import com.onneshon.blog.payloads.JwtAuthResponse;
import com.onneshon.blog.payloads.JwtLoginRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailServiceImple userDetailServiceImple;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody JwtLoginRequest userInfo) throws Exception{
	
		String userName = userInfo.getUserName();
		String password = userInfo.getPassword();
		
		//authentication kortesi j User er password thik ache ki na
		this.authenticate(userName, password);
		
		
		String token = jwtUtil.generateToken(userDetailServiceImple.loadUserByUsername(userName));
		
		JwtAuthResponse response = new JwtAuthResponse(token);
		
		return ResponseEntity.ok(response);
		
	}
	
	
	private void authenticate(String userName, String password) throws Exception {		
		try {			
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		}catch (DisabledException e) {
			throw new Exception("User Disabled", e);
		}catch (BadCredentialsException e) {
			throw new Exception("Bad Credential", e);
		}
	}
	
}
