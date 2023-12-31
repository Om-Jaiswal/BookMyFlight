package com.jaiswal.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.jaiswal.user.bean.User;
import com.jaiswal.user.repository.UserRepository;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Autowired
    private UserRepository userRepository;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username -> {
            User user = userRepository.findByUsername(username);
            if (user != null) {
                return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRoles().toArray(new String[0]))
                        .build();
            } else {
            	logger.warn("User Not Found!");
                throw new UsernameNotFoundException("User Not Found!");
            }
        }).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	.cors().and()
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/user-profile/signin").permitAll()
            .antMatchers(HttpMethod.POST, "/user-profile/signout").permitAll()
            .antMatchers(HttpMethod.POST, "/user-profile/signup").permitAll()
            .antMatchers(HttpMethod.POST, "/user-profile/update").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }
    
    @Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
    
}
