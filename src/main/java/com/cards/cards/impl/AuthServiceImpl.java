package com.cards.cards.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cards.cards.entity.Users;
import com.cards.cards.exceptions.CustomException;
import com.cards.cards.pojos.JwtResponse;
import com.cards.cards.pojos.LoginDto;
import com.cards.cards.pojos.SignupDto;
import com.cards.cards.repos.UserDetailsImpl;
import com.cards.cards.repos.UserRepository;
import com.cards.cards.security.JwtUtils;
import com.cards.cards.services.AuthTokenService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthTokenService {
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final UserRepository userRepository;
	private final AuthenticationManager authenticationManager;
	private final JwtUtils jwtUtils;

	/**
	 * @param signupDto
	 * @return
	 */
	@Override
	public SignupDto createUserAccount(SignupDto signupDto) {
		Optional<Users> res = userRepository.findByUsername(signupDto.getUsername().toLowerCase());
		if (res.isPresent()) {
			throw new CustomException("A user with the given username already exists.Try a different one.");
		}
		Users users = modelMapper.map(signupDto, Users.class);
		users.setUsername(users.getUsername());
		users.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		userRepository.save(users);
		return modelMapper.map(signupDto, SignupDto.class);
	}

	/**
	 * @param loginDto
	 * @return
	 */
	@Override
	public ResponseEntity<JwtResponse> authenticateUser(LoginDto loginDto) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginDto.getUsername().toLowerCase(Locale.ROOT).trim(), loginDto.getPassword().trim()));
		} catch (BadCredentialsException e) {
			log.error("Bad credentials " + e.getMessage());
			throw new CustomException("Invalid username or password.");
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		LocalDateTime presentDateTime = LocalDateTime.now();
		JwtResponse result = JwtResponse.builder().username(userDetails.getUsername()).status_code(0).role(null)
				.type("BEARER").token(jwt)
				.issuedAt(presentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")))
				.expiry(presentDateTime.plusSeconds(jwtUtils.getTokenValidityInSecs())
						.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")))
				.build();
		return ResponseEntity.ok(result);

	}

}