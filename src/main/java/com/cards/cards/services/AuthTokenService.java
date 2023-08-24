package com.cards.cards.services;

import org.springframework.http.ResponseEntity;

import com.cards.cards.pojos.JwtResponse;
import com.cards.cards.pojos.LoginDto;
import com.cards.cards.pojos.SignupDto;

public interface AuthTokenService {
	/**
	 *
	 * @param signupDto
	 * @return
	 */
	SignupDto createUserAccount(SignupDto signupDto);

	/**
	 *
	 * @param loginDto
	 * @return
	 */
	ResponseEntity<JwtResponse> authenticateUser(LoginDto loginDto);
}