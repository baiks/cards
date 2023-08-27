package com.cards.cards.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
	@NotBlank(message = "Name cannot be blank")
	@Size(min = 1, message = "Minimum of 1 character is expected")
	private String name;
	@Pattern(regexp = "^#[A-Za-z0-9+_.-]+$", message = "Color must be alphanumeric characters prefixed with a #")
	@NotBlank(message = "Color cannot be blank")
	@Size(min = 7, max = 7, message = "7 characters expected")
	private String color;
	private String description;
}
