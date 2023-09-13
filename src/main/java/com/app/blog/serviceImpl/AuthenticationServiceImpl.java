package com.app.blog.serviceImpl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.blog.entity.Role;
import com.app.blog.entity.User;
import com.app.blog.payloads.JwtAuthenticationResponse;
import com.app.blog.payloads.SignUpRequest;
import com.app.blog.payloads.SigninRequest;
import com.app.blog.repositries.UserRepo;
import com.app.blog.services.AuthenticationService;
import com.app.blog.services.JwtService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService{

	private final UserRepo userrepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	
	@Override
	public JwtAuthenticationResponse signup(SignUpRequest request) {
		// TODO Auto-generated method stub
		var user =User.builder().name(request.getName()).email(request.getEmail()).about(request.getAbout()).password(passwordEncoder.encode(request.getPassword())).role(Role.USER).build();
		System.out.println("at service user"+user.toString());
this.userrepository.save(user);
var jwt =jwtService.generateToken(user);
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

	@Override
	public JwtAuthenticationResponse signin(SigninRequest request) {
		// TODO Auto-generated method stub
		this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		var user =userrepository.findByEmail(request.getEmail()).orElseThrow(()-> new IllegalArgumentException("envalid User name and password. "));
		var jwt=jwtService.generateToken(user);
		
		
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

}
