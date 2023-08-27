package com.cards.cards.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cards.cards.dtos.JwtResponse;
import com.cards.cards.dtos.LoginDto;
import com.cards.cards.dtos.SignUpDto;
import com.cards.cards.services.AuthTokenService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AUTH", description = "User management")
public class AuthController {

	private final AuthTokenService authService;

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	@Operation(summary = "Create a user", description = "Returns the details of a user.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "{\n" + "  \"username\": \"XjytRAF73lFF3mp15uv9K6LIikJN-J\",\n"
			+ "  \"password\": \"tm&v&gPVFD%c6sm\",\n" + "  \"role\": \"ADMIN\"\n" + "}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "POST: /signup") })
	public SignUpDto register(@Valid @RequestBody SignUpDto signupDto) {
		return authService.createUserAccount(signupDto);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	@Operation(summary = "Login user", description = "Returns the JWT.\n" + "\n" + "Example Requests:\n" + "\n" + "{\n"
			+ "  \"username\": \"XkBjGVUYKPF7-H_2YLduecgvXQd6OL\",\n" + "  \"password\": \"Gg1wprYtN$xedty\"\n" + "}")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "POST: /login") })
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto userDto) {
		return authService.authenticateUser(userDto);
	}
}