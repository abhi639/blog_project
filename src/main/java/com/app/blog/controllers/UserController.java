package com.app.blog.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.blog.payloads.ApiResponce;
import com.app.blog.payloads.UserDto;
import com.app.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService useService;
	
	
	@PostMapping("/")
	public ResponseEntity<UserDto>createuser(@Valid @RequestBody UserDto userDto){
		
		
		UserDto createUserDto=this.useService.createUser(userDto);
		return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
		
	}
	
	
	
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
	
	
}

 