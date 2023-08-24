package com.cards.cards.pojos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupDto {

	@Pattern(regexp = "\\w+", message = "Name should not contain any special character")
	@NotBlank(message = "System name cannot be blank")
	@Size(min = 3, max = 20)
	private String username;

	@NotBlank(message = "Please enter a password")
	@Size(min = 6, max = 40, message = "Minimum of 6 characters expected")
	private String password;

}