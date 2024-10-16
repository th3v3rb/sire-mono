package com.dantesoft.sireauthservice.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserRequest {
	@NotBlank(message = "The username its required")
	public String username;
	@NotBlank(message = "The email its required")
	public String email;
	@NotBlank(message = "The password its required")
	public String password;
	@NotBlank(message = "Password confirmation its required")
	public String passwordConfirmation;
}
