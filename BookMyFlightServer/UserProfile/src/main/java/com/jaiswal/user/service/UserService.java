package com.jaiswal.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.jaiswal.user.bean.Details;
import com.jaiswal.user.bean.SigninResponse;
import com.jaiswal.user.bean.User;
import com.jaiswal.user.bean.UserCredentials;

public interface UserService {
	
	public ResponseEntity<SigninResponse> signin(UserCredentials credentials);
	public ResponseEntity<String> signout(HttpServletRequest request);
	public ResponseEntity<String> signup(User newUser);
	public ResponseEntity<String> update(String username, Details details);
	
}
