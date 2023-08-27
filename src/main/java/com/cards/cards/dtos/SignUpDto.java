package com.cards.cards.dtos;

import com.cards.cards.configs.UserRoles;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpDto {

	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Username should be a valid email address")
	@NotBlank(message = "Username cannot be blank")
	@Size(min = 6, max = 30, message = "Minimum of 6 and maximum of 30 characters expected")
	private String username;

	@NotBlank(message = "Please enter a password")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "Password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
	@Size(min = 6, max = 15, message = "Minimum of 6 and maximum of 15 characters expected")
	private String password;
	private UserRoles role;

}