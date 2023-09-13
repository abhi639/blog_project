package com.app.blog.services;

import com.app.blog.payloads.JwtAuthenticationResponse;
import com.app.blog.payloads.SignUpRequest;
import com.app.blog.payloads.SigninRequest;

public interface AuthenticationService {
	 JwtAuthenticationResponse signup(SignUpRequest request);

	    JwtAuthenticationResponse signin(SigninRequest request);
}
