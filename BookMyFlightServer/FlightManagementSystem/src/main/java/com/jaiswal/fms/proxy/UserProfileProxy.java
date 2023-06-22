package com.jaiswal.fms.proxy;

import javax.servlet.http.HttpServletRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jaiswal.fms.bean.UserCredentials;

@FeignClient(name="user-profile", url="localhost:8200")
public interface UserProfileProxy {
	
	@PostMapping("/user-profile/login")
    public ResponseEntity<String> login(@RequestBody UserCredentials credentials);
	
	@PostMapping("/user-profile/logout")
    public ResponseEntity<String> logout(HttpServletRequest request);
	
	@PostMapping("/user-profile/signup")
    public ResponseEntity<String> signup(@RequestBody UserCredentials credentials);

}
