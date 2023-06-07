package com.app.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class UserDto {

	
	private int id;
	@NotEmpty
	@Size(min=4,message="User name must be 4 character")
	private String name;
     @Email(message = "Email address is not valid")
	private String email;
     @NotEmpty
     @Size(min=3,max=10,message = "password must be min 3 character")
	private String password;
     @NotEmpty
	
     private String about;
	
	public UserDto() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public UserDto(int id, @NotEmpty @Size(min = 4, message = "User name must be 4 character") String name,
			@Email(message = "Email address is not valid") String email,
			@NotEmpty @Size(min = 3, max = 10, message = "password must be min 3 character") String password,
			@NotEmpty String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	
	
	
}
