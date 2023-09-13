package com.app.blog.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payloads.ApiResponce;
import com.app.blog.payloads.JwtAuthenticationResponse;
import com.app.blog.payloads.SignUpRequest;
import com.app.blog.payloads.SigninRequest;
import com.app.blog.payloads.UserDto;
import com.app.blog.services.AuthenticationService;
import com.app.blog.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService useService;
	
	@Autowired
	private AuthenticationService authenticationService;
	
	SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

//	@PostMapping("/")
//	public ResponseEntity<UserDto>createuser(@Valid @RequestBody UserDto userDto){
//		
//		
//		UserDto createUserDto=this.useService.createUser(userDto);
//		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
//		
//	}
	
	
	
	@PutMapping("/{UserId}")
	 public ResponseEntity<UserDto>updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("UserId") Integer UserId){
		UserDto updatedUser=this.useService.updateUser(userDto, UserId) ;
		 
		return ResponseEntity.ok(updatedUser);
	 }
	@DeleteMapping("/{userId}")
	public ResponseEntity<?>deleteuser(@PathVariable("userId") Integer uid){
		
		
		
		this.useService.deleteUser(uid);
		
		return new ResponseEntity<ApiResponce>(new ApiResponce("User Delete successfully",true),HttpStatus.OK);
	}
	 @GetMapping("/")
	public ResponseEntity<List<UserDto>>getallUser(){
		
		
		return ResponseEntity.ok(this.useService.getAlluser());
		
		
	}
	 @GetMapping("/{userId}")
		public ResponseEntity<UserDto> getallUser(@PathVariable("userId") Integer uid){
			
			
			return ResponseEntity.ok(this.useService.getuserbyId(uid));
			
			
		}
	
	 @PostMapping("/signup")
	 public ResponseEntity<JwtAuthenticationResponse>signup(@RequestBody SignUpRequest request){
		 System.out.println("user Data: "+request);
		return ResponseEntity.ok(authenticationService.signup(request)); 
		 
		 
	 }
	 @PostMapping("/signin")
	 public ResponseEntity<JwtAuthenticationResponse>signin(@RequestBody SigninRequest request){
		 
		 return ResponseEntity.ok(authenticationService.signin(request));
		 
	 }
	 @PostMapping("/logout")
	 public String performLogout(Authentication authentication, HttpServletRequest request, HttpServletResponse response) {
	     // .. perform logout
	     this.logoutHandler.logout(request, response, authentication);
	     return "logout";
	 }
	
}

 