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

import com.cards.cards.pojos.JwtResponse;
import com.cards.cards.pojos.LoginDto;
import com.cards.cards.pojos.SignupDto;
import com.cards.cards.services.AuthTokenService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "AUTH", description = "User management")
public class AuthController {

	private final AuthTokenService authService;

	@RequestMapping(method = RequestMethod.POST, value = "/signup")
	@Operation(summary = "Create a user", description = "Returns the details of a user.\n" + "\n"
			+ "Example Requests:\n" + "\n" + "user")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "POST: /signup") })
	public SignupDto register(@Valid @RequestBody SignupDto signupDto) {
		return authService.createUserAccount(signupDto);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	@Operation(summary = "Login user", description = "Returns the JWT.\n" + "\n" + "Example Requests:\n" + "\n" + "jwt")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "POST: /login") })
	public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto) {
		return authService.authenticateUser(loginDto);
	}
}