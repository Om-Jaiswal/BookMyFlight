package com.jaiswal.user.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SigninResponse {
	
	private String jwtToken;
	private String username;
	private Details details;
	
}
