package com.jaiswal.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jaiswal.user.bean.Details;
import com.jaiswal.user.bean.SigninResponse;
import com.jaiswal.user.bean.User;
import com.jaiswal.user.bean.UserCredentials;
import com.jaiswal.user.service.UserService;

@RestController
@RequestMapping("/user-profile")
public class UserController {
	
	@Autowired
	private UserService service;

    @PostMapping("/signin")
    public ResponseEntity<SigninResponse> signin(@RequestBody UserCredentials credentials) {
        return service.signin(credentials);
    }

    @PostMapping("/signout")
    public ResponseEntity<String> signout(HttpServletRequest request) {
    	return service.signout(request);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User newUser) {
    	return service.signup(newUser);
    }
    
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestParam String username, @RequestBody Details details) {
    	return service.update(username, details);
    }
   
}
