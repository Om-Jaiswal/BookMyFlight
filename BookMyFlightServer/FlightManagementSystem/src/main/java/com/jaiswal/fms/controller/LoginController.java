package com.jaiswal.fms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.fms.bean.UserCredentials;
import com.jaiswal.fms.proxy.UserProfileProxy;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/bmf")
public class LoginController {
	
	@Autowired
	private UserProfileProxy profile;
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
		return profile.login(credentials);
	}
	
	@PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
		return profile.logout(request);
	}
}
