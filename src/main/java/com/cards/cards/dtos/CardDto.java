package com.cards.cards.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
	@NotBlank(message = "{validation.constraint.invalid.blank.name}")
	@Size(min = 1, message = "{validation.constraint.invalid.blank.name}")
	private String name;
	@Pattern(regexp = "^#[A-Za-z0-9+_.-]+$", message = "{validation.constraint.invalid.regex.color}")
	@NotBlank(message = "{validation.constraint.invalid.blank.color}")
	@Size(min = 7, max = 7, message = "{validation.constraint.invalid.length.color}")
	private String color;
	private String description;
}
