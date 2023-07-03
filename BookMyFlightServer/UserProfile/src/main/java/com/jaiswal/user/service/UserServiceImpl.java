package com.jaiswal.user.service;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.jaiswal.user.bean.Details;
import com.jaiswal.user.bean.SigninResponse;
import com.jaiswal.user.bean.User;
import com.jaiswal.user.bean.UserCredentials;
import com.jaiswal.user.repository.UserRepository;
import com.jaiswal.user.util.JwtTokenUtil;
import com.jaiswal.user.util.PasswordEncoderUtil;

@Service
public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    
    public UserServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }
	
	@Autowired
	private UserRepository userRepository;
	
	/**
	 * Method to sigin user by authenticating credentials
	 * @param credentials - credentials object which will contain user name and password
	 * @return response - response contain jwtToken, username and details
	 */
	public ResponseEntity<SigninResponse> signin(UserCredentials credentials) {
		String username = credentials.getUsername();
		String password = credentials.getPassword();
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Generate and return the JWT token
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        
        Details details = userRepository.findByUsername(username).getDetails();
        
        SigninResponse response = new SigninResponse(jwtToken, username, details);
        
        logger.warn("Signin Successfully!");
        
        return ResponseEntity.ok(response);
	}
	
	/**
	 * Method to signout user
	 * @param request - request to signout the user
	 * @return response - success message
	 */
	public ResponseEntity<String> signout(HttpServletRequest request) {
		// Extract the JWT token from the request
        String token = jwtTokenUtil.extractJwtToken(request);

        // Validate and invalidate the JWT token
        if (jwtTokenUtil.validateToken(token)) {
            jwtTokenUtil.invalidateToken(token);
        	logger.warn("Signout Successfully!");
            return ResponseEntity.ok().body("{\"message\": \"Signout Successful!\"}");
        }
        
        logger.warn("Invalid Token!");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Token!");
	}
	
	/**
	 * Method to signup new user in the database
	 * @param newUser - newUser details to signup
	 * @return response - success message
	 */
	public ResponseEntity<String> signup(User newUser) {
		User user = new User();
		
		user.setUsername(newUser.getUsername());
		
		String encryptedPassword = PasswordEncoderUtil.encodePassword(newUser.getPassword());
		user.setPassword(encryptedPassword);
		
		user.addRole("USER");
		
		user.setDetails(newUser.getDetails());
		
		userRepository.save(user);
		
		logger.warn("New User Registered Successfully!");
		
		return ResponseEntity.ok().body("{\"message\": \"New User Registered Successfully!\"}");
	}
	
	/**
	 * Method to update user details
	 * @param username - username for which data will be updated
	 * @param details - details which need to be updated
	 * @return response - success message
	 */
	public ResponseEntity<String> update(String username, Details details) {
		
		User user = userRepository.findByUsername(username);
		user.setDetails(details);
		userRepository.save(user);
		
		logger.warn("User Updated Successfully!");
		
		return ResponseEntity.ok().body("{\"message\": \"User Updated Successfully!\"}");
	
	}
	
}
