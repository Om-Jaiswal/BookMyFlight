package com.jaiswal.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.jaiswal.user.bean.UserCredentials;

public interface UserService {
	
	public ResponseEntity<String> login(UserCredentials credentials);
	public ResponseEntity<String> logout(HttpServletRequest request);
	public ResponseEntity<String> signup(UserCredentials credentials);
	
}
