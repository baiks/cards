package com.cards.cards.pojos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

	@NotBlank(message = "System name cannot be blank")
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank(message = "Please enter a password")
	@Size(min = 6, max = 40, message = "Password should be between 6 and 40")
	private String password;

}