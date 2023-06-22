package com.jaiswal.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.user.bean.UserCredentials;
import com.jaiswal.user.service.UserService;

@RestController
@RequestMapping("/user-profile")
public class UserController {
	
	@Autowired
	private UserService service;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials) {
        return service.login(credentials);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
    	return service.logout(request);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserCredentials credentials) {
    	return service.signup(credentials);
    }
   
}
