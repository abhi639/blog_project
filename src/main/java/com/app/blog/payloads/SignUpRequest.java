package com.app.blog.payloads;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {

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
}

