package com.cards.cards.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginDto {

	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "{validation.constraint.invalid.regex.username}")
	@NotBlank(message = "{validation.constraint.invalid.blank.username}")
	@Size(min = 6, max = 30, message = "{validation.constraint.invalid.length.username}")
	private String username;

	@NotBlank(message = "{validation.constraint.invalid.blank.password}")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$", message = "{validation.constraint.invalid.regex.password}")
	@Size(min = 6, max = 15, message = "{validation.constraint.invalid.length.password}")
	private String password;

}