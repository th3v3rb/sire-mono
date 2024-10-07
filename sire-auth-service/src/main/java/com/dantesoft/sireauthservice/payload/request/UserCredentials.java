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
public class UserCredentials {
	@NotBlank(message = "The email its required")
	public String email;
	@NotBlank(message = "The password its required")
	public String password;
}
