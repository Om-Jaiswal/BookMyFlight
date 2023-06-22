package com.jaiswal.user.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jaiswal.user.bean.User;
import com.jaiswal.user.bean.UserCredentials;
import com.jaiswal.user.repository.UserRepository;
import com.jaiswal.user.util.JwtTokenUtil;
import com.jaiswal.user.util.PasswordEncoderUtil;

@Service
public class UserServiceImpl implements UserService {
	
	private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    
    public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }
	
	@Autowired
	private UserRepository userRepository;
	
	public ResponseEntity<String> login(UserCredentials credentials) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Generate and return the JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(jwtToken);
	}
	
	public ResponseEntity<String> logout(HttpServletRequest request) {
		// Extract the JWT token from the request
        String token = jwtTokenUtil.extractJwtToken(request);

        // Validate and invalidate the JWT token
        if (jwtTokenUtil.validateToken(token)) {
            jwtTokenUtil.invalidateToken(token);
            return ResponseEntity.ok().body("{\"message\": \"Logout Successful!\"}");
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token!");
	}
	
	public ResponseEntity<String> signup(UserCredentials credentials) {
		User user = new User();
		
		user.setUsername(credentials.getUsername());
		
		String encryptedPassword = PasswordEncoderUtil.encodePassword(credentials.getPassword());
		user.setPassword(encryptedPassword);
		
		user.addRole("USER");
		
		userRepository.save(user);
		
		return ResponseEntity.ok("User Added Successfully!");
	}
	
}
