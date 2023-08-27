package com.cards.cards.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cards.cards.configs.Constants;
import com.cards.cards.configs.UserRoles;
import com.cards.cards.dtos.JwtResponse;
import com.cards.cards.dtos.LoginDto;
import com.cards.cards.dtos.SignUpDto;
import com.cards.cards.entity.Users;
import com.cards.cards.exceptions.CustomException;
import com.cards.cards.repos.UserDetailsImpl;
import com.cards.cards.repos.UserRepository;
import com.cards.cards.security.JwtUtils;
import com.cards.cards.services.AuthTokenService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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
	public SignUpDto createUserAccount(SignUpDto signupDto) {
		Users users = modelMapper.map(signupDto, Users.class);
		users.setPassword(passwordEncoder.encode(signupDto.getPassword()));
		userRepository.save(users);
		return modelMapper.map(signupDto, SignUpDto.class);
	}

	/**
	 * @param loginDto
	 * @return
	 */
	@Override
	public ResponseEntity<JwtResponse> authenticateUser(LoginDto userDto) {
		Authentication authentication = null;
		try {
			authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userDto.getUsername().toLowerCase(Locale.ROOT).trim(), userDto.getPassword().trim()));
		} catch (BadCredentialsException e) {
			log.error("Bad credentials " + e.getMessage());
			throw new CustomException("Invalid username or password.");
		}
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		String role = userDetails.getAuthorities().stream().findFirst().get().getAuthority();
		LocalDateTime presentDateTime = LocalDateTime.now();
		JwtResponse result = JwtResponse.builder().username(userDetails.getUsername()).status_code(0).role(role)
				.type("BEARER").token(jwt)
				.issuedAt(presentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")))
				.expiry(presentDateTime.plusSeconds(Constants.Jwt.expiry)
						.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a")))
				.build();
		return ResponseEntity.ok(result);

	}

}