package com.onneshon.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OnneshonBlogApisApplication implements CommandLineRunner{

	@Autowired
//	private static PasswordEncoder myPasswordEncoder;
	private PasswordEncoder myPasswordEncoder;
	String x;
	
	public static void main(String[] args) {		
		SpringApplication.run(OnneshonBlogApisApplication.class, args);
		
		//ekhane o use kora jay...but static method hote hobe
//		myPasswordEncoder.encode();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(myPasswordEncoder.encode("1234"));;		
	}
	
	
}
