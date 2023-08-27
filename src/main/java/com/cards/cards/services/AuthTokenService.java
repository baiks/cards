package com.cards.cards.services;

import org.springframework.http.ResponseEntity;

import com.cards.cards.dtos.JwtResponse;
import com.cards.cards.dtos.LoginDto;
import com.cards.cards.dtos.SignUpDto;

public interface AuthTokenService {
	/**
	 *
	 * @param signupDto
	 * @return
	 */
	SignUpDto createUserAccount(SignUpDto signupDto);

	/**
	 *
	 * @param loginDto
	 * @return
	 */
	ResponseEntity<JwtResponse> authenticateUser(LoginDto userDto);
}